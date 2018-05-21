package com.mhmtnasif;

public class Test {

	public static void main(String[] args) {
		Database.getConnection().set(1, 'c');
		Database.getConnection().set(2, 'a');
		System.out.println(Database.getConnection().get(1));
		System.out.println(Database.getConnection().get(2));
		for (int i = 1; i < 10; i++) {
			System.out.println(i+"  "+Database.getConnection());
		}

	}

}
