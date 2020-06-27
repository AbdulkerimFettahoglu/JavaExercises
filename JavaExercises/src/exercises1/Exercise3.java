package exercises1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * This class holds line information to provide easy way to manage.
 * @author afettah
 *
 */
class Account implements Comparable<Account> {
	public static final int controlDigitStart = 0;
	public static final int bankCodeStart = 3;
	public static final int ownerCodeStart = 12;	
	
	String controlDigit;
	String bankCode;
	String ownerCode;
	
	/**
	 * This constructor will parse the line and store the useful information.
	 * @param line
	 */
	public Account(String line) {
		this.controlDigit = line.substring(Account.controlDigitStart, Account.controlDigitStart+2);
		this.bankCode = line.substring(Account.bankCodeStart, Account.bankCodeStart+8);
		this.ownerCode = line.substring(Account.ownerCodeStart,Account.ownerCodeStart+19);
	}

	@Override
	public String toString() {
		return "Account [controlDigit=" + controlDigit + ", bankCode=" + bankCode + ", ownerCode=" + ownerCode + "]";
	}
	
	public String toStringForExercise() {
		return this.controlDigit + " " + this.bankCode + " " + this.ownerCode;
	}

	@Override
	public int compareTo(Account obj) {
		if(Integer.parseInt(this.controlDigit)>Integer.parseInt(obj.controlDigit))
			return 1; 
		else if (Integer.parseInt(this.controlDigit)<Integer.parseInt(obj.controlDigit))
			return -1;
		;
		if (Integer.parseInt(this.bankCode)>Integer.parseInt(obj.bankCode))
			return 1;
		else if (Integer.parseInt(this.bankCode)<Integer.parseInt(obj.bankCode))
			return -1;
		;
		if (Long.parseLong(this.ownerCode.replaceAll("\\s+",""))>Long.parseLong(obj.ownerCode.replaceAll("\\s+","")))
			return 1;
		else if (Long.parseLong(this.ownerCode.replaceAll("\\s+",""))<Long.parseLong(obj.ownerCode.replaceAll("\\s+","")))
			return -1;
		return 0;
	}
	
	public boolean equals(Account obj) {
		if (Integer.parseInt(this.controlDigit)==Integer.parseInt(obj.controlDigit) 
				&& Integer.parseInt(this.bankCode)==Integer.parseInt(obj.bankCode)
				&& Long.parseLong(this.ownerCode.replaceAll("\\s+",""))>Long.parseLong(obj.ownerCode.replaceAll("\\s+",""))) {
					return true;
		}
		return false;
	}
}

/**
 * This class contains information about list of accounts and count how many time they repeated.
 * @author afettah
 *
 */
class TestCase {
	/**
	 * This map stores the records in test case as sorted.
	 */
	TreeMap<Account, Integer> sortedRecords = new TreeMap<>();
	int recordCount;
	
	/**
	 * This function reads test case records from start to the end. 
	 * @param reader
	 * @throws NumberFormatException This exception will throw if there is at least one non well formed records. 
	 * @throws IOException
	 */
	public void readCase(BufferedReader reader) throws NumberFormatException, IOException {
		/* Get how many records we have in the test case. */
		this.recordCount = Integer.parseInt(reader.readLine());
		Account ac;
		for (int i = 0; i < this.recordCount; i++) {
			/* Create a new Account object for every line of record. */
			ac = new Account(reader.readLine());
			/* Let's check did we see same person before? */
			if(this.sortedRecords.containsKey(ac)) {
				/* Increase repeat count of person. */
				Integer value = this.sortedRecords.get(ac);
				value++;
				this.sortedRecords.put(ac, value);
			} else {
				/* Add new person to our hash map. */
				this.sortedRecords.put(ac, 1);
			}
		}
		
		/* Let's write what we got from the test case. */
		Iterator<Account> iterator = sortedRecords.keySet().iterator();
		while(iterator.hasNext()) {
		    Account key   = (Account) iterator.next();
		    Integer value = sortedRecords.get(key);
		    System.out.println(key.toStringForExercise() + " " + value);
		}
	}
}

/**
 * This class manages generic setting about the file.
 * @author afettah
 *
 */
class Test {
	String fileName;
	int testCount;
	
	/**
	 * Give file name to be parse.
	 * @param fileName
	 */
	public Test(String fileName) {
		this.fileName = fileName;
		this.readFile();
	}
	
	public void readFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
			String line = reader.readLine();
			/* Get how many test case we have in the file. */
			this.testCount = Integer.parseInt(line);
			TestCase tc;
			for (int counter = 0; counter < this.testCount; counter++) {
				/* Create new TestCase object for every case in the file. */
				tc = new TestCase();
				/* Let's read/parse records of the test case. */
				tc.readCase(reader);
				/* Don't write a new empty line if last test case is processing. */
				if (counter!=this.testCount-1)
					System.out.println();
				reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occured while reading file. File name :" + this.fileName);
			e.printStackTrace();
		}
	}
}

public class Exercise3 {
	public static void main (String[] args) {
		Test t = new Test("myfile.txt");
	}
}
