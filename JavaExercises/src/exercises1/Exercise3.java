package exercises1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

class Account implements Comparable<Account> {
	public static final int controlDigitStart = 0;
	public static final int bankCodeStart = 3;
	public static final int ownerCodeStart = 12;	
	
	String controlDigit;
	String bankCode;
	String ownerCode;
	
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

class TestCase {
	TreeMap<Account, Integer> sortedRecords = new TreeMap<>();
	int testNumber;
	int recordCount;
	
	public TestCase(int testNumber) {
		this.testNumber = testNumber;
	}
	
	public void readCase(BufferedReader reader) throws NumberFormatException, IOException {
		this.recordCount = Integer.parseInt(reader.readLine());
		Account ac;
		for (int i = 0; i < this.recordCount; i++) {
			ac = new Account(reader.readLine());
			if(this.sortedRecords.containsKey(ac)) {
				Integer value = this.sortedRecords.get(ac);
				value++;
				this.sortedRecords.put(ac, value);
			} else {
				this.sortedRecords.put(ac, 1);
			}
		}
		
		Iterator<Account> iterator = sortedRecords.keySet().iterator();
		while(iterator.hasNext()) {
		    Account key   = (Account) iterator.next();
		    Integer value = sortedRecords.get(key);
		    System.out.println(key.toStringForExercise() + " " + value);
		}
	}
}

class Test {
	String fileName;
	int testCount;
	List<TestCase> cases;
	
	public Test(String fileName) {
		this.fileName = fileName;
		this.readFile();
	}
	
	public void readFile() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(this.fileName));
			String line = reader.readLine();
			this.testCount = Integer.parseInt(line);
			this.cases = new ArrayList<TestCase>(this.testCount);
			for (int counter = 0; counter < this.testCount; counter++) {
				TestCase tc = new TestCase(counter);
				this.cases.add(tc);
				tc.readCase(reader);
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
