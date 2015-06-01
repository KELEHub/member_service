package com.member.service.frame.database.entity;



public class QueryCondition
implements Comparable<QueryCondition>
{
private String fieldNode;
private String fieldName;
private String operation;
private Object value;
private String valueType;
private Integer weight = Integer.valueOf(100000);

public String getFieldNode() {
  return this.fieldNode;
}

public void setFieldNode(String fieldNode) {
  this.fieldNode = fieldNode;
}

public String getFieldName() {
  return this.fieldName;
}

public void setFieldName(String fieldName) {
  this.fieldName = fieldName;
}

public String getOperation() {
  return this.operation;
}

public void setOperation(String operation) {
  this.operation = operation;
}

public int compareTo(QueryCondition arg)
{
  int weight = valueOfQueryType(this.operation) - valueOfQueryType(arg.getOperation());
  return weight == 0 ? this.weight.intValue() - arg.weight.intValue() : weight;
}

private int valueOfQueryType(String queryType)
{
  if (("eq".equals(queryType)) || ("neproperty".equals(queryType)))
    return 0;
  if (("ne".equals(queryType)) || ("neproperty".equals(queryType)))
    return 1;
  if ("between".equals(queryType))
    return 2;
  if ("in".equals(queryType))
    return 3;
  if (("ge".equals(queryType)) || ("gt".equals(queryType)) || ("geproperty".equals(queryType)) || ("gtproperty".equals(queryType)))
    return 4;
  if (("le".equals(queryType)) || ("lt".equals(queryType)) || ("leproperty".equals(queryType)) || ("ltproperty".equals(queryType)))
    return 5;
  if ("like".equals(queryType))
    return 6;
  if ("ilike".equals(queryType))
    return 7;
  if ("query".equals(queryType)) {
    return 100;
  }
  return 999;
}

public String getValueType() {
	return valueType;
}

public void setValueType(String valueType) {
	this.valueType = valueType;
}

public void setValue(Object value) {
  this.value = value;
}

public Object getValue() {
  return this.value;
}
}