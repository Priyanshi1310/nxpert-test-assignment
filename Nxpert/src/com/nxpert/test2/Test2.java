package com.nxpert.test2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Scanner;

public class Test2 {
	
	public static Date StringToDate(String dob) throws ParseException{
	     
		//Instantiating the SimpleDateFormat class
	      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	     
	      //Parsing the given String to Date object
	      Date date = formatter.parse(dob);
	      System.out.println("Date object value: "+date);
	      return date;
	   }

	public static void main(String[] args) throws ParseException {
		
		  Scanner sc = new Scanner(System.in);
	      System.out.println("Enter your DOB (dd-MM-yyyy): ");
	      String dob = sc.next();
	      
	      //Converting String to Date
	      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	      Date date = formatter.parse(dob);
	      
	      //Converting obtained Date object to LocalDate object
	      Instant instant = date.toInstant();
	      ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
	      LocalDate givenDate = zone.toLocalDate();
	      
	      //Calculating the difference between given date to current date.
	      Period period = Period.between(givenDate, LocalDate.now());
	      System.out.print("You are: ");
	      System.out.print(period.getYears()+" years "+period.getMonths()+" months "+period.getDays()+" days "+ "old");
	   }

	}

