package com.bill.ema.emaServer.controller;

import java.util.Scanner;

public class Main {
	public static void main(String[] arg) {
		 Scanner in = new Scanner(System.in);
         String s = in.next();
         int k = in.nextInt();
         String tail =  s.substring(0,k);
         String head = s.substring(k);
         System.out.println(head+tail);
	}

}
