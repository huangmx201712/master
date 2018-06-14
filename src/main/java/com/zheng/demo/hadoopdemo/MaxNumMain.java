/*
package com.zheng.demo.hadoopdemo;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxNumMain {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("输出参数不符合");
            System.exit(-1);
        }
        Job job = Job.getInstance();

        job.setJarByClass(MaxNumMain.class);
        job.setJar("Max Num");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(MaxNumMapper.class);
        job.setReducerClass(MaxNumReducer.class);
        job.setCombinerClass(MaxNumCombiner.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputKeyClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
*/
