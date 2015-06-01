package com.member.service.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



/**
 * @author lyj 支持linux和windows下的ping命令
 */
public class Ping {
	private static String			osname		= null;
	private static int				thread_max	= 100;
	private static ExecutorService	executor;
	static {
		executor = Executors.newFixedThreadPool(thread_max);
	}




	public String getOsName() {
		if (osname == null) {
			osname = System.getProperties().getProperty("os.name");
		}
		return osname;
	}

	public boolean isReachable(final String destIp, final int maxCount) {
		boolean bool = false;
		Future<Boolean> future = executor.submit(new Callable<Boolean>() {

			
			public Boolean call() throws Exception {
				return reachable(destIp, maxCount);
			}
		});
		try {
			bool = future.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * @param destIp
	 * @param maxCount
	 * @return
	 */
	public boolean reachable(String destIp, int maxCount) {
		boolean reachable = false;
		LineNumberReader input = null;
		Process process = null;
		try {
			String pingCmd = null;
			if (getOsName().startsWith("Windows")) {
				pingCmd = "ping -n {0} {1}";
				pingCmd = MessageFormat.format(pingCmd, maxCount, destIp);
			} else if (getOsName().startsWith("Linux")) {
				pingCmd = "ping -c {0} {1}";
				pingCmd = MessageFormat.format(pingCmd, maxCount, destIp);
			} else {
				System.out.println("not support OS");
				return false;
			}
			process = Runtime.getRuntime().exec(pingCmd);
			InputStreamReader ir = new InputStreamReader(
					process.getInputStream());
			input = new LineNumberReader(ir);
			String line;
			while ((line = input.readLine()) != null) {
				if (!"".equals(line)) {
					if (getOsName().startsWith("Windows")) {
						reachable = parseWindowsMsg(line, maxCount);
					} else if (getOsName().startsWith("Linux")) {
						reachable = parseLinuxMsg(line, maxCount);
					}
				}
				if (reachable)
					break;
			}

		} catch (IOException e) {
			e.printStackTrace();
			

		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException ex) {
					ex.printStackTrace();
					

				}
			}
			if (process != null) {
				process.destroy();
			}
		}
		return reachable;
	}

	private boolean parseWindowsMsg(String reponse, int total) {
		return (reponse.startsWith("来自") && !reponse.contains("无法访问目标"))
				|| (reponse.startsWith("Reply from") && !reponse
						.contains("unreachable"));
	}

	private boolean parseLinuxMsg(String reponse, int total) {
		return reponse.contains("bytes from")
				&& !reponse.contains("unreachable")
				&& reponse.contains("icmp_seq=");
	}

	/**
	 * @param filepath
	 * @return list
	 */
	public List<String> getIpListFromTxt(String filepath) {
		BufferedReader br = null;
		List<String> iplist = new ArrayList<String>();
		try {
			File file = new File(filepath);
			br = new BufferedReader(new FileReader(file));
			while (br.ready()) {
				String line = br.readLine();
				if (null != line && !"".equals(line)) {
					iplist.add(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);

		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (Exception ex) {
					ex.printStackTrace(System.out);
				}
			}
		}
		return iplist;
	}

}
