package myStrCmp;

import java.util.ArrayList;

/**
 * @author Victor I. Wood <wood.vi.jobs@gmail.com>
 *
 * Compare String implementation with interfaces for:
 *  the command line,
 *  an object,
 *  a library
 *  
 *  from the command line, run it as  
 *   MyStrCmp abc 123
 *  from the command line, and it will output the strings as specified
 *  (but won't return the comparison value)
 *  
 *  as an object, call it as:
 *   MyStrCmp x = new MyStrCmp("abc");
 *   int y = x.myStrCmp("123");
 *  will give the same outputs, and y will be set to the comparison value
 *  further, the comparison value will be available as
 *   x.getLastResult()
 *  and the output string(s) will be available as
 *   x.getOutputStrings( n ); // where n is the number of strings, can be one or two (or none uninitialized)
 *  
 *  as a static libary function, you may call:
 *   MyStrCmp.myStrCmp( "abc", "123");
 *  and it will return -1, 0, +1 as specified
 *  (but doesn't return the output strings)
 *
 *   2014-Jan-07 VIW: adds handling for special case where s1 is a subset of s2
 *   2014-Jan-06 VIW: created
 */

public class MyStrCmp {
	
	// comparison results
	private static final int COMPARISON_THISLESSER = -1;
	private static final int COMPARISON_THISGREATER = +1;
	private static final int COMPARISON_MATCH = 0;
	
	// error values would never come up in the command line context, as main
	// will exit if we don't get valid inputs
	private static final int ERRORVAL = -9;
	private static final int UNITIALIZEDVAL = -8;

	// overkill appliance for unit testing the output
	private int outputValue = UNITIALIZEDVAL; // keeping it handy, just in case
												// somebody wants to see it
												// again
	private static final int DEFAULT_LIST_CAPACITY = 2;
	private ArrayList<String> outputStringList;

	// "this" string
	private String stringValue;

	/**
	 *  empty constructor
	 *  (don't forget to call setStringValue before using)
	 */
	public MyStrCmp() {
		stringValue = null;
		outputStringList = new ArrayList<String>(DEFAULT_LIST_CAPACITY);
	}

	/**
	 *  string constructor
	 *  @param s 	the String to set as this bject's internal value
	 */
	public MyStrCmp(String s) {
		// TODO Auto-generated constructor stub
		stringValue = s;
		outputStringList = new ArrayList<String>(DEFAULT_LIST_CAPACITY);
	}

	/**
	 * Creates an object with the first parameter, and then compares it to the
	 * second parameter
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		int numArgs = args.length;
		if (numArgs == 2) {
			MyStrCmp myStrCmp = new MyStrCmp(args[0]);
			myStrCmp.myStrCmp(args[1]);
			return; // (x.myStrCmp(args[1]));
		} else {
			System.err
					.printf("Recieved %d strings.  MyStrCmp requires exactly two strings.\n",
							numArgs);
			return; // ERRORVAL;
		}

	}

	/**
	 * Compares this MyStrCmp object to the compareString
	 * 
	 * Has the additional effects of storing the comparison value for later retrieval from getLastResult(), and storing the output string(s) for later retrieval from getOutputStrings(idx), and of outputting the output string(s) to stdout 
	 * 
	 * @param compareString 	a string to compare this MyStrCmp against
	 * @return 		returns -1 if MyStrCmp is lexicographically before compareString, 0 if they match, and +1 if after
	 */
	public int myStrCmp(String compareString) {
		outputStringList = new ArrayList<String>(DEFAULT_LIST_CAPACITY);
		outputValue = myStrCmp(this.stringValue, compareString);

		if (ERRORVAL == outputValue) {
			// error values would never come up in the command line context, as
			// main will exit if we don't get valid inputs, but if this were
			// used as an object, then this could happen. Appropriate messages
			// would have already been printed in the static library myStrCmp
			// function, so nothing to do here.
			; // N-Op

		} else if (outputValue < 0) {
			// If the return value is <0, then returned strings are
			// inverted, IE abcdef and uvwxyz, would be displayed as fedcba and
			// zyxwvu
			outputStringList.add(reverseString(this.stringValue));
			outputStringList.add(reverseString(compareString));

			System.out.printf("%s %s\n", outputStringList.get(0),
					outputStringList.get(1));

		} else if (outputValue == 0) {
			// If the return value is 0, then one string is returned and is
			// made up with the merged 2 input strings; IE abcdef and uvwxyz,
			// would be displayed as aubvcwdxeyfz.
			outputStringList.add(mergeStrings(this.stringValue, compareString));
			System.out.printf("%s\n", outputStringList.get(0));

		} else if (outputValue > 0) {
			// If the return value is >0, then one string is returned and is
			// made up with the merged 2 input strings; IE abcdef and
			// uvwxyz, would be displayed as zfyexdwcvbua
			outputStringList.add(reverseString(mergeStrings(compareString,
					this.stringValue)));
			System.out.printf("%s\n", outputStringList.get(0));

		} else {
			// should never get here, because it's either ==0, <0 or >0
			throw new IllegalStateException("invalid outputValue in myStrCmp");
		}

		return (outputValue);
	}
	
	/**
	 * Compares this MyStrCmp object to the compareString
	 * 
	 * Has no additional effects (doesn't store, output any strings) 
	 * 
	 * @param compareString 	a string to compare this MyStrCmp against
	 * @return 		returns -1 if MyStrCmp is lexicographically before compareString, 0 if they match, and +1 if after
	 */
	public static int myStrCmp(String stringValue, String compareString) {

		// error checking, treats null as "before" anything else, including the empty string
		if ((null == stringValue) && (null == compareString)) {
			System.err.println("Warning: Boths strings are null");
			return COMPARISON_MATCH;
		} else if ((null == stringValue)) {
			System.err.println("Warning: stringValue (first parameter) is null");
			return COMPARISON_THISLESSER;
		} else if ((null == compareString)) {
			System.err.println("Warning: compareString (second parameter) is null");
			return COMPARISON_THISGREATER;
		} else {

			int index = 0;
			int outputValue = COMPARISON_MATCH;
			while ((COMPARISON_MATCH == outputValue) && (index < stringValue.length())
					&& (index < compareString.length())) {
				if ((stringValue.charAt(index)) > (compareString.charAt(index))) {
					outputValue = COMPARISON_THISGREATER;
				} else if ((stringValue.charAt(index)) < (compareString
						.charAt(index))) {
					outputValue = COMPARISON_THISLESSER;
				}
				index++;
			}
			// if s1 is a subset of s2, then the shorter string is "less"
			if( COMPARISON_MATCH == outputValue ) {				
				if( stringValue.length() < compareString.length() ) {
					outputValue = COMPARISON_THISLESSER;
					
				} else if( stringValue.length() > compareString.length() ) {
					outputValue = COMPARISON_THISGREATER;
				
				}
				
			}

			return (outputValue);
		}
	}

	
	/**
	 * stringValue getter
	 * 
	 * @return the stringValue
	 */
	public String getStringValue() {
		return stringValue;
	}

	/**
	 * stringValue setter
	 * 
	 * @param stringValue the stringValue to set
	 */
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	/**
	 * outputValue getter
	 * 
	 * @return the stringValue
	 */
	public int getLastResult() {
		return outputValue;
	}

	/**
	 * tells us the number of output strings
	 * 
	 * @return the number of output strings
	 */
	public int numOutputStrings() {
		if (outputStringList == null) {
			return 0;
		} else {
			return outputStringList.size();
		}
	}

	/**
	 * gets a specified output string
	 * 
	 * @param idx the outputString to get
	 * @return the outputString
	 */
	public String getOutputStrings(int idx) { // my understanding of the spec is that we never want exceptions, so this specifically does NOT throw null-pointer or out-of-bounds 
		if (outputStringList == null) {
			System.err.println("Warning: outputStringList is currently unset");	// we've been pretty aggressive about setting this, so this should never happen
			return "";	// return the blank string in that case
		} else if ( idx >= outputStringList.size() ) {
			System.err.println("Warning: outputStringList is currently empty");
			return "";	// return the blank string in that case
		} else {
			return outputStringList.get(idx);
		}
	}

	/**
	 * returns the string reversed, ie: 'abc' becomes 'cba'
	 * 
	 * @param s a String to reverse
	 * @return returns the reversed String
	 */
	protected static String reverseString(String s) {
		int index;

		// error checking
		if (null == s) {
			System.err.println("Warning: null string sent to reverseString");
			return ""; // return the blank string in that case
		} else {

			int len = s.length();
			StringBuilder outputString = new StringBuilder(len);

			// walk backwards down the string
			for (index = len; index > 0; index--) {
				outputString.append(s.charAt(index - 1));
			}
			return (outputString.toString());
		}
	}

	/**
	 * merges the primary and secondary string, starting with the primary, eg
	 * 'abc' and '123' get merged into 'a1b2c3'
	 * 
	 * @param s1
	 *            primary string
	 * @param s2
	 *            secondary string
	 * @return the merged String
	 */
	protected static String mergeStrings(String s1, String s2) {
		int index;
		int len1;
		int len2;
		int len;

		// very cautious exception handling, as per spec
		if (null == s1) {
			System.err.println("Warning: s1 is null in mergeStrings");
			len1 = 0;
		} else {
			len1 = s1.length();
		}

		if (null == s2) {
			System.err.println("Warning: s2 is null in mergeStrings");
			len2 = 0;
		} else {
			len2 = s2.length();
		}

		len = Math.max(len1, len2);
		StringBuilder outputString = new StringBuilder(len1 + len2);

		// walk through both strings
		for (index = 0; index < len; index++) {
			if (index < len1) {
				outputString.append(s1.charAt(index));
			}
			if (index < len2) {
				outputString.append(s2.charAt(index));
			}
		}
		return (outputString.toString());
	}

}
