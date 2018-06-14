/*
package com.zheng.demo.hadoopdemo;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.regex.Pattern;

public class MaxNumMapper extends  Mapper<LongWritable,Text,Text,IntWritable> {

    public static  final int MISSING=9999;


    @Override
    public void map(LongWritable longWritable, Text text,Context context) throws IOException,InterruptedException {
        */
/**
         * longWritable输入键 text输入值
         *//*

        String  line=text.toString();
        String pattern="^\\d+$";
       if(Pattern.matches(pattern,line)){
           context.write(new Text(longWritable.toString()),new IntWritable(Integer.valueOf(line.toString())));
       }

    }
}
*/
