package com.WebChat.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.jsp.jstl.sql.Result;

import com.WebChat.pojo.Record;

public class RecordDao extends BaseDao{
	public int addRecord(Record record){
		int i=-1;
		String sql="insert into record (words) values ( ? )";
		String[] args={record.getWords()};
		i=update(sql, args);
		return i;
	}
	public List<Record> findAllRecords(){
		List<Record> records=null;
		String sql="select record_id, words from record";
		Result result=query(sql, null);
		records=r2o(result);
		return records;
	}
	private List<Record> r2o(Result result) {
		List<Record> records=new ArrayList<Record>();
		if (result==null||result.getRowCount()==0) {
			return null;
		}
		@SuppressWarnings("rawtypes")
		SortedMap[] sortedMaps=result.getRows();
		for (int i = 0; i < sortedMaps.length; i++) {
			@SuppressWarnings("rawtypes")
			SortedMap recordMap=sortedMaps[i];
			records.add(r2o(recordMap));
		}
		return records;
	}
	private Record r2o(@SuppressWarnings("rawtypes") SortedMap recordMap) {
		Record record=new Record();
		if (recordMap==null) {
			return null;
		}
		record.setRocord_id(recordMap.get("record_id")==null?-1:Integer.parseInt(recordMap.get("record_id").toString()));
		record.setWords(recordMap.get("words")==null?"":recordMap.get("words").toString());
		return record;
	}
	
}
