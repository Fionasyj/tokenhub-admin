package com.yinzhi.platform.global;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ArrayUtil {
	
	private Stack<Object> st = new Stack<Object>();

	/**
	 * 将传入数组，按顺序排列
	 * @param data 对像数组
	 * @param num 数量
	 * @param split 分隔符
	 * @param list 返回数组
	 * @return
	 */
	public List<Object> arrayToString(Object[] data, int num, String split, List<Object> list) {
		if (num > data.length || num <= 0) {
			return list;
		}
		if (num == 1) {
			for (int i = 0; i < data.length; i++) {
				StringBuffer sb = new StringBuffer();
				st.push(data[i]);
				for (int j = 0; j < st.size(); j++) {
					if (sb.length() > 0) {
						sb.append(split);
					}
					sb.append(st.get(j));
				}
				System.out.println();
				list.add(sb.toString());
				st.pop();
			}
			return list;
		}
		for (int i = 0; i < data.length - num + 1; i++) {
			st.push(data[i]);
			Object[] newStr = new Object[data.length - i - 1];
			int k = 0;
			for (int j = i + 1; j < data.length; j++) {
				newStr[k++] = data[j];
			}
			arrayToString(newStr, num - 1, split,  list);
			st.pop();
		}
		return list;
	}

	public static void main(String[] args) {
		String data[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11" };
		ArrayUtil tzh = new ArrayUtil();
		List<Object> list = new ArrayList<Object>();
		tzh.arrayToString(data, 10, ",", list);

		System.out.println();
	}

}
