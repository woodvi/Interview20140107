/**
 * 
 */
package myStrCmp;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Victor I. Wood <wood.vi.jobs@gmail.com>
 *
 *   2014-Jan-07 VIW: adds test for special case where s1 is a subset of s2
 *   2014-Jan-06 VIW: created
 * 
 */
public class MyStrCmpTests {

	/**
	 * Test method for {@link myStrCmp.MyStrCmp#myStrCmp(java.lang.String)}.
	 */
	@Test
	public void testMyStrCmp_abcdef_uvwxyz() {

		MyStrCmp obj = new MyStrCmp("abcdef");

		// result should be -1, because abcdef < uvwxyz
		assertEquals(
				"MyStrCmp.myStrCmp fails to identify 'abcdef' as before 'uvwxyz'.",
				-1, obj.myStrCmp("uvwxyz"));

		assertEquals(
				"MyStrCmp.myStrCmp didn't return two seperate inverted strings.",
				2, obj.numOutputStrings());

		assertEquals("MyStrCmp.myStrCmp didn't properly invert first string.",
				"fedcba", obj.getOutputStrings(0));

		assertEquals("MyStrCmp.myStrCmp didn't properly invert second string.",
				"zyxwvu", obj.getOutputStrings(1));

	}

	/**
	 * Test method for {@link myStrCmp.MyStrCmp#myStrCmp(java.lang.String)}.
	 */
	@Test
	public void testMyStrCmp_uvwxyz_abcdef() {

		MyStrCmp obj = new MyStrCmp("uvwxyz");

		// result should be -1, because abcdef < uvwxyz
		assertEquals(
				"MyStrCmp.myStrCmp fails to identify 'uvwxyz' as after 'abcdef'.",
				+1, obj.myStrCmp("abcdef"));

		assertEquals(
				"MyStrCmp.myStrCmp didn't return one merged, inverted string.",
				1, obj.numOutputStrings());

		assertEquals("MyStrCmp.myStrCmp didn't properly invert second string.",
				"zfyexdwcvbua", obj.getOutputStrings(0));
	}

	/**
	 * Test method for {@link myStrCmp.MyStrCmp#myStrCmp(java.lang.String)}.
	 */
	@Test
	public void testMyStrCmp_abc_123() {

		MyStrCmp obj = new MyStrCmp("abc");

		// result should be +1, because abc > 123
		assertEquals(
				"MyStrCmp.myStrCmp fails to identify 'abc' as after '123'.",
				+1, obj.myStrCmp("123"));

		assertEquals(
				"MyStrCmp.myStrCmp didn't return a single merged inverted string.",
				1, obj.numOutputStrings());

		assertEquals("MyStrCmp.myStrCmp didn't properly merge & invert strings.",
				"c3b2a1", obj.getOutputStrings(0));

	}

	/**
	 * Test method for {@link myStrCmp.MyStrCmp#myStrCmp(java.lang.String)}.
	 */
	@Test
	public void testMyStrCmp_abc_abc() {

		MyStrCmp obj = new MyStrCmp("abc");

		// result should be -1, because abcdef < uvwxyz
		assertEquals(
				"MyStrCmp.myStrCmp fails to identify 'abc' as matching 'abc'.",
				0, obj.myStrCmp("abc"));

		assertEquals(
				"MyStrCmp.myStrCmp didn't return a single merged string.",
				1, obj.numOutputStrings());

		assertEquals("MyStrCmp.myStrCmp didn't properly invert first string.",
				"aabbcc", obj.getOutputStrings(0));

	}

	/**
	 * Test method for {@link myStrCmp.MyStrCmp#myStrCmp(java.lang.String)}.
	 */
	@Test
	public void testMyStrCmp_ijk_Misc() {

		MyStrCmp obj = new MyStrCmp("ijk");

		// result should be -1, because ijk < uvwxyz
		assertEquals(
				"MyStrCmp.myStrCmp fails to identify 'ijk' as before 'uvwxyz'.",
				-1, obj.myStrCmp("uvwxyz"));

		// result should be 0, because ijk == ijk
		obj.myStrCmp("ijk");
		assertEquals(
				"MyStrCmp.myStrCmp fails to identify 'ijk' as matching 'ijk'.",
				0, obj.getLastResult());

		// result should be -1, because ijk > ab
		obj.myStrCmp("ab");
		assertEquals(
				"MyStrCmp.myStrCmp fails to identify 'ijk' as after 'ab'.",
				+1, obj.getLastResult());

	}

	/**
	 * Test method for {@link myStrCmp.MyStrCmp#myStrCmp(java.lang.String)}.
	 */
	@Test
	public void testMyStrCmp_ijk_static() {

		// result should be -1, because ijk < uvwxyz
		assertEquals(
				"MyStrCmp.myStrCmp fails to identify 'ijk' as before 'uvwxyz'.",
				-1, MyStrCmp.myStrCmp("ijk", "uvwxyz"));

		// result should be 0, because ijk == ijk
		assertEquals(
				"MyStrCmp.myStrCmp fails to identify 'ijk' as matching 'ijk'.",
				0, MyStrCmp.myStrCmp("ijk", "ijk"));

		// result should be -1, because ijk > ab
		assertEquals(
				"MyStrCmp.myStrCmp fails to identify 'ijk' as after 'ab'.",
				+1, MyStrCmp.myStrCmp("ijk", "ab"));

	}

	/**
	 * Test method for {@link myStrCmp.MyStrCmp#myStrCmp(java.lang.String)}.
	 */
	@Test
	public void testMyStrCmp_ijk_subset() {

		// result should be -1, because ijk < uvwxyz
		assertEquals(
				"MyStrCmp.myStrCmp fails to identify 'ijk' as after 'ij'.",
				+1, MyStrCmp.myStrCmp("ijk", "ij"));

		// result should be 0, because ijk == ijk
		assertEquals(
				"MyStrCmp.myStrCmp fails to identify 'ijk' as before 'ijkl'.",
				-1, MyStrCmp.myStrCmp("ijk", "ijkl"));

		// result should be -1, because ijk > ab
		assertEquals(
				"MyStrCmp.myStrCmp fails to identify 'ijk' as after an empty string.",
				+1, MyStrCmp.myStrCmp("ijk", ""));
		
		// result should be -1, because ijk > ab
		assertEquals(
				"MyStrCmp.myStrCmp fails to identify the null string as before 'ijk'.",
				-1, MyStrCmp.myStrCmp(null, "ijk"));
		

	}
	
	/**
	 * Test method for {@link myStrCmp.MyStrCmp#reverseString(java.lang.String)}
	 * .
	 */
	@Test
	public void testReverseString_abc() {
		assertEquals("reverseString doesn't properly reverse 'abc'", "cba",
				MyStrCmp.reverseString("abc"));

	}

	/**
	 * Test method for {@link myStrCmp.MyStrCmp#reverseString(java.lang.String)}
	 * .
	 */
	@Test
	public void testReverseString_123() {
		assertEquals("reverseString doesn't properly reverse '123'", "321",
				MyStrCmp.reverseString("123"));
	}

	/**
	 * Test method for
	 * {@link myStrCmp.MyStrCmp#reverseStrings(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testRevereStrings_null() {
		assertEquals(
				"reverseString should (but doesn't) return a blank string when reversing a null string",
				"", MyStrCmp.reverseString(null));
	}

	/**
	 * Test method for
	 * {@link myStrCmp.MyStrCmp#reverseStrings(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testRevereStrings_empty() {
		assertEquals(
				"reverseString should (but doesn't) return a blank string when reversing a blank string",
				"", MyStrCmp.reverseString(""));
	}

	/**
	 * Test method for
	 * {@link myStrCmp.MyStrCmp#mergeStrings(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testMergeStrings_123_abc() {
		assertEquals("mergeStrings doesn't properly merge '123' with 'abc'",
				"1a2b3c", MyStrCmp.mergeStrings("123", "abc"));
	}

	/**
	 * Test method for
	 * {@link myStrCmp.MyStrCmp#mergeStrings(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testMergeStrings_abc_123() {
		assertEquals("mergeStrings doesn't properly merge 'abc' with '123'",
				"a1b2c3", MyStrCmp.mergeStrings("abc", "123"));
	}

	/**
	 * Test method for
	 * {@link myStrCmp.MyStrCmp#mergeStrings(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testMergeStringsMismatchedLengths() {
		assertEquals("mergeStrings doesn't properly merge 'abc' with '1'",
				"a1bc", MyStrCmp.mergeStrings("abc", "1"));
		assertEquals("mergeStrings doesn't properly merge '1' with 'abc'",
				"1abc", MyStrCmp.mergeStrings("1", "abc"));
		assertEquals(
				"mergeStrings doesn't properly merge '123' with an empty string",
				"123", MyStrCmp.mergeStrings("123", ""));
		assertEquals(
				"mergeStrings doesn't properly merge an empty string with '123'",
				"123", MyStrCmp.mergeStrings("", "123"));
	}

	/**
	 * Test method for
	 * {@link myStrCmp.MyStrCmp#mergeStrings(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testMergeStrings_x_null() {
		assertEquals(
				"mergeStrings should (but doesn't) treat the null string as a blank string",
				"x", MyStrCmp.mergeStrings("x", null));
	}

	/**
	 * Test method for
	 * {@link myStrCmp.MyStrCmp#mergeStrings(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testMergeStrings_null_x() {
		assertEquals(
				"mergeStrings should (but doesn't) treat the null string as a blank string",
				"x", MyStrCmp.mergeStrings(null, "x"));
	}

}
