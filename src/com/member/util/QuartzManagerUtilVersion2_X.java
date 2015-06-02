package com.member.util;
/*package com.web.jz.bs.nms.module.util;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.hp.hpl.sparta.ParseException;

*//**
 * 定时任务管理�?�?��quartz 2.x 版本才能使用
 * 
 * 
 *//*
 @SuppressWarnings("unchecked") public class QuartzManagerUtilVersion2_X {
	private final static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	private final static String JOB_GROUP_NAME = "JZ_NMS_JOB";
	private final static String TRIGGER_GROUP_NAME = "JZ_NMS_TRIGGER";
	static {
		startJobs();
	}

	*//**
	 * 添加�?��定时任务，使用默认的任务组名，触发器名，触发器组�?	 * 
	 * @param jobName
	 *            任务�?	 * @param jobClass
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 *//*
	public static void addJob(String jobName, String jobClass, String time) {
		try {

			JobDetail jobDetail = JobBuilder.newJob(
					(Class<? extends Job>) Class.forName(jobClass))
					.withIdentity(jobName, JOB_GROUP_NAME).build();
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(
					jobName, TRIGGER_GROUP_NAME).startNow().withSchedule(
					CronScheduleBuilder.cronSchedule(time)).build();
			Scheduler sched = gSchedulerFactory.getScheduler();

			sched.scheduleJob(jobDetail, trigger);
			// 启动
			if (!sched.isStarted()) {
				sched.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	*//**
	 * 添加�?��定时任务
	 * 
	 * @param jobName
	 *            任务�?	 * @param jobGroupName
	 *            任务组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组�?	 * @param jobClass
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 *//*
	public static void addJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName, String jobClass,
			String time) {
		try {
			JobDetail jobDetail = JobBuilder.newJob(
					(Class<? extends Job>) Class.forName(jobClass))
					.withIdentity(jobName, jobGroupName).build();

			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(
					triggerName, triggerGroupName).startNow().withSchedule(
					CronScheduleBuilder.cronSchedule(time)).build();
			Scheduler sched = gSchedulerFactory.getScheduler();

			sched.scheduleJob(jobDetail, trigger);
			// 启动
			if (!sched.isStarted()) {
				sched.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	*//**
	 * 添加�?��定时任务，使用默认的任务组名，触发器名，触发器组�?	 * 
	 * @param jobName
	 *            任务名称
	 * @param jobClass
	 *            任务�?	 * @param intervalSeconds
	 *            执行间隔时间，单位秒�?	 * @param maxrepeatCount
	 *            重复次数，注意：如果�?表示不执行，-1表示不限制次�?（直到过期），默认为0
	 *//*
	public static void addJob(String jobName, String jobClass,
			int intervalSeconds, int maxrepeatCount) {
		try {

			JobDetail jobDetail = JobBuilder.newJob(
					(Class<? extends Job>) Class.forName(jobClass))
					.withIdentity(jobName, JOB_GROUP_NAME).build();

			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName,
					TRIGGER_GROUP_NAME).startNow().withSchedule(
					SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(intervalSeconds) // 时间间隔
							.withRepeatCount(maxrepeatCount) // 重复次数(将执�?�?
					).build();

			Scheduler sched = gSchedulerFactory.getScheduler();

			sched.scheduleJob(jobDetail, trigger);
			// 启动
			if (!sched.isStarted()) {
				sched.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	*//**
	 * 添加�?��定时任务
	 * 
	 * @param jobName
	 *            任务�?	 * @param jobGroupName
	 *            任务组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组�?	 * @param jobClass
	 *            任务
	 * @param intervalSeconds
	 *            执行间隔时间，单位秒�?	 * @param maxrepeatCount
	 *            重复次数，注意：如果�?表示不执行，-1表示不限制次�?（直到过期），默认为0
	 *//*
	public static void addJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName, String jobClass,
			int intervalSeconds, int maxrepeatCount) {
		try {
			JobDetail jobDetail = JobBuilder.newJob(
					(Class<? extends Job>) Class.forName(jobClass))
					.withIdentity(jobName, jobGroupName).build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(
					triggerName, triggerGroupName).startNow().withSchedule(
					SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(intervalSeconds) // 时间间隔
							.withRepeatCount(maxrepeatCount) // 重复次数(将执�?�?
					).build();
			Scheduler sched = gSchedulerFactory.getScheduler();

			sched.scheduleJob(jobDetail, trigger);
			// 启动
			if (!sched.isStarted()) {
				sched.start();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	*//**
	 * 修改�?��任务的触发时�?使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * @param time
	 *//*
	public static void modifyJobTime(String jobName, String time) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey
					.triggerKey(jobName, TRIGGER_GROUP_NAME));
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				JobDetail jobDetail = sched.getJobDetail(JobKey.jobKey(jobName,
						JOB_GROUP_NAME));
				Class objJobClass = jobDetail.getJobClass();
				String jobClass = objJobClass.getName();
				removeJob(jobName);

				addJob(jobName, jobClass, time);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	*//**
	 * 修改�?��任务的触发时�?	 * 
	 * @param triggerName
	 * @param triggerGroupName
	 * @param time
	 *//*
	public static void modifyJobTime(String triggerName,
			String triggerGroupName, String time) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey
					.triggerKey(triggerName, triggerGroupName));
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {

				// 修改时间
				((TriggerBuilder) trigger).withSchedule(CronScheduleBuilder
						.cronSchedule(time));
				// 重启触发�?				sched.resumeTrigger(TriggerKey.triggerKey(triggerName,
						triggerGroupName));
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	*//**
	 * 移除�?��任务(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 *//*
	public static void removeJob(String jobName) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.pauseTrigger(TriggerKey.triggerKey(jobName,
					TRIGGER_GROUP_NAME));// 停止触发�?			sched.unscheduleJob(TriggerKey.triggerKey(jobName,
					TRIGGER_GROUP_NAME));// 移除触发�?			sched.deleteJob(JobKey.jobKey(jobName, JOB_GROUP_NAME));// 删除任务
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	*//**
	 * 移除�?��任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 *//*
	public static void removeJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched
					.pauseTrigger(TriggerKey.triggerKey(jobName,
							triggerGroupName));// 停止触发�?			sched.unscheduleJob(TriggerKey
					.triggerKey(jobName, triggerGroupName));// 移除触发�?			sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	*//**
	 * 启动�?��定时任务
	 *//*
	public static void startJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			if (!sched.isStarted()) {
				sched.start();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	*//**
	 * 关闭�?��定时任务
	 *//*
	public static void shutdownJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
*/