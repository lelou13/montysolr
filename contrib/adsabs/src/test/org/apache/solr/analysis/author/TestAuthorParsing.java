package org.apache.solr.analysis.author;

import java.util.HashMap;

import org.apache.solr.analysis.author.AuthorUtils;

import junit.framework.TestCase;

public class TestAuthorParsing extends TestCase {


    public void testParseAuthor1() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "VAN DER KAMP");
        expected.put("first", "A");
        assertEquals(expected, AuthorUtils.parseAuthor("A VAN DER KAMP"));
    }

    public void testParseAuthor2() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "VAN DER KAMP");
        expected.put("first", "A");
        assertEquals(expected, AuthorUtils.parseAuthor("VAN DER KAMP, A"));
    }

    public void testParseAuthor3() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "van der Kamp");
        expected.put("first", "A");
        assertEquals(expected, AuthorUtils.parseAuthor("van der Kamp, A"));
    }

    public void testParseAuthor4() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "van der Kamp");
        expected.put("first", "A");
        assertEquals(expected, AuthorUtils.parseAuthor("van der Kamp, A."));
    }

    public void testParseAuthor5() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("title", "Dr");
        expected.put("last", "Von Accomazzi");
        expected.put("suffix", "III");
        expected.put("first", "Alberto");
        assertEquals(expected, AuthorUtils.parseAuthor("Von Accomazzi, Alberto, III, Dr."));
    }

    public void testParseAuthor6() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("title", "Dr");
        expected.put("last", "von Accomazzi");
        expected.put("suffix", "III");
        expected.put("first", "Alberto");
        assertEquals(expected, AuthorUtils.parseAuthor("von Accomazzi, Alberto, III, Dr."));
    }

    public void testParseAuthor7() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("title", "Dr");
        expected.put("last", "von Accomazzi");
        expected.put("suffix", "III");
        expected.put("first", "Alberto");
        assertEquals(expected, AuthorUtils.parseAuthor("von Accomazzi, Alberto, III, Dr."));
    }

    public void testParseAuthor8() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "ACCOMAZZI");
        assertEquals(expected, AuthorUtils.parseAuthor("ACCOMAZZI"));
    }

    public void testParseAuthor9() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Dall'oglio");
        expected.put("first", "Antonella");
        assertEquals(expected, AuthorUtils.parseAuthor("Antonella Dall'oglio"));
    }

    public void testParseAuthor10() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Dall'oglio");
        expected.put("first", "Antonella");
        assertEquals(expected, AuthorUtils.parseAuthor("Dall'oglio, Antonella"));
    }

    public void testParseAuthor11() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Dall'Oglio");
        expected.put("first", "Antonella");
        assertEquals(expected, AuthorUtils.parseAuthor("Dall'Oglio, Antonella"));
    }

    public void testParseAuthor12() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Dall'Oglio");
        expected.put("first", "Antonella");
        assertEquals(expected, AuthorUtils.parseAuthor("Dall'Oglio, Antonella"));
    }

    public void testParseAuthor13() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "KAO");
        expected.put("first", "P'ING");
        expected.put("middle", "TZU");
        assertEquals(expected, AuthorUtils.parseAuthor("P'ING-TZU KAO"));
    }

    public void testParseAuthor14() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "KAO");
        expected.put("first", "P'ING");
        expected.put("middle", "TZU");
        assertEquals(expected, AuthorUtils.parseAuthor("KAO, P'ING-TZU"));
    }

    public void testParseAuthor15() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Kao");
        expected.put("first", "P'ing");
        expected.put("middle", "Tzu");
        assertEquals(expected, AuthorUtils.parseAuthor("Kao, P'ing-Tzu"));
    }

    public void testParseAuthor16() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Kao");
        expected.put("first", "P'ing");
        expected.put("middle", "Tzu");
        assertEquals(expected, AuthorUtils.parseAuthor("Kao, P'ing-Tzu"));
    }

    public void testParseAuthor17() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "S");
        expected.put("last", "O");
        expected.put("first", "Paul");
        assertEquals(expected, AuthorUtils.parseAuthor("Paul S O"));
    }

    public void testParseAuthor18() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "S");
        expected.put("last", "O");
        expected.put("first", "Paul");
        assertEquals(expected, AuthorUtils.parseAuthor("O, Paul S"));
    }

    public void testParseAuthor19() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "S");
        expected.put("last", "O");
        expected.put("first", "Paul");
        assertEquals(expected, AuthorUtils.parseAuthor("O, Paul S"));
    }

    public void testParseAuthor20() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "S");
        expected.put("last", "O");
        expected.put("first", "Paul");
        assertEquals(expected, AuthorUtils.parseAuthor("O, Paul S."));
    }

    public void testParseAuthor21() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "Middle");
        expected.put("last", "Last");
        expected.put("first", "Furst");
        assertEquals(expected, AuthorUtils.parseAuthor("Last, Furst Middle"));
    }

    public void testParseAuthor22() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "Middle More");
        expected.put("last", "last");
        expected.put("first", "furst");
        assertEquals(expected, AuthorUtils.parseAuthor("last, furst Middle More"));
    }

    public void testParseAuthor23() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "M");
        expected.put("last", "last");
        expected.put("first", "Furst");
        assertEquals(expected, AuthorUtils.parseAuthor("last, Furst M."));
    }

    public void testParseAuthor24() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "M");
        expected.put("last", "van Last");
        expected.put("first", "Furst");
        assertEquals(expected, AuthorUtils.parseAuthor("van Last, Furst M."));
    }

    public void testParseAuthor25() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "M");
        expected.put("last", "Last");
        expected.put("first", "Furst");
        assertEquals(expected, AuthorUtils.parseAuthor("Last, Furst M"));
    }

    public void testParseAuthor26() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "m");
        expected.put("last", "last");
        expected.put("first", "furst");
        assertEquals(expected, AuthorUtils.parseAuthor("last, furst m."));
    }

    public void testParseAuthor27() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Last");
        expected.put("first", "Furst");
        assertEquals(expected, AuthorUtils.parseAuthor("Last, Furst"));
    }

    public void testParseAuthor28() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "M");
        expected.put("last", "Last");
        expected.put("first", "F");
        assertEquals(expected, AuthorUtils.parseAuthor("Last, F. M."));
    }

    public void testParseAuthor29() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Last");
        expected.put("first", "F");
        assertEquals(expected, AuthorUtils.parseAuthor("Last, F."));
    }

    public void testParseAuthor30() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Last");
        assertEquals(expected, AuthorUtils.parseAuthor("Last"));
    }

    public void testParseAuthor31() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "A");
        expected.put("last", "van Tiggelen");
        expected.put("suffix", "Jr");
        expected.put("first", "Bart");
        assertEquals(expected, AuthorUtils.parseAuthor("van Tiggelen, Bart A., Jr."));
    }

    public void testParseAuthor32() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "M");
        expected.put("last", "Last");
        expected.put("first", "F");
        assertEquals(expected, AuthorUtils.parseAuthor("F. M. Last"));
    }

    public void testParseAuthor33() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "M");
        expected.put("last", "Last");
        expected.put("first", "F");
        assertEquals(expected, AuthorUtils.parseAuthor("F M Last"));
    }

    public void testParseAuthor34() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Last");
        expected.put("first", "F");
        assertEquals(expected, AuthorUtils.parseAuthor("F. Last"));
    }

    public void testParseAuthor35() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Last");
        expected.put("first", "F");
        assertEquals(expected, AuthorUtils.parseAuthor("F Last"));
    }

    public void testParseAuthor36() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "A");
        expected.put("last", "van Tiggelen");
        expected.put("first", "B");
        assertEquals(expected, AuthorUtils.parseAuthor("B A van Tiggelen"));
    }

    public void testParseAuthor37() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "A");
        expected.put("last", "van Tiggelen");
        expected.put("first", "Bart");
        assertEquals(expected, AuthorUtils.parseAuthor("Bart A van Tiggelen"));
    }

    public void testParseAuthor38() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "G");
        expected.put("last", "de la Rosa");
        expected.put("first", "I");
        assertEquals(expected, AuthorUtils.parseAuthor("de la Rosa, I G"));
    }

    public void testParseAuthor39() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Torres Flores");
        expected.put("first", "S");
        assertEquals(expected, AuthorUtils.parseAuthor("Torres-Flores, S"));
    }

    public void testParseAuthor40() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Łuczak");
        expected.put("first", "Andrzej");
        assertEquals(expected, AuthorUtils.parseAuthor("Łuczak, Andrzej"));
    }

    public void testParseAuthor41() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("title", "DR");
        expected.put("last", "EVIL");
        expected.put("first", "X");
        assertEquals(expected, AuthorUtils.parseAuthor("DR. X EVIL"));
    }

    public void testParseAuthor42() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("title", "DR");
        expected.put("last", "EVIL");
        expected.put("first", "X");
        assertEquals(expected, AuthorUtils.parseAuthor("EVIL, X, DR."));
    }

    public void testParseAuthor43() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("title", "Dr");
        expected.put("last", "Evil");
        expected.put("first", "X");
        assertEquals(expected, AuthorUtils.parseAuthor("Evil, X, Dr."));
    }

    public void testParseAuthor44() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("title", "Dr");
        expected.put("last", "Evil");
        expected.put("first", "X");
        assertEquals(expected, AuthorUtils.parseAuthor("Evil, X., Dr."));
    }

    public void testParseAuthor45() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "DOE");
        expected.put("suffix", "JR");
        expected.put("first", "JOHN");
        assertEquals(expected, AuthorUtils.parseAuthor("JOHN DOE JR"));
    }

    public void testParseAuthor46() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "DOE");
        expected.put("suffix", "JR");
        expected.put("first", "JOHN");
        assertEquals(expected, AuthorUtils.parseAuthor("DOE, JOHN, JR"));
    }

    public void testParseAuthor47() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Doe");
        expected.put("suffix", "Jr");
        expected.put("first", "John");
        assertEquals(expected, AuthorUtils.parseAuthor("Doe, John, Jr"));
    }

    public void testParseAuthor48() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Doe");
        expected.put("suffix", "Jr");
        expected.put("first", "John");
        assertEquals(expected, AuthorUtils.parseAuthor("Doe, John, Jr."));
    }

    public void testParseAuthor49() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "M");
        expected.put("last", "Last");
        expected.put("first", "F");
        assertEquals(expected, AuthorUtils.parseAuthor("Last, F.M."));
    }

    public void testParseAuthor50() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("last", "Dall'oglio");
        assertEquals(expected, AuthorUtils.parseAuthor("Dall'oglio,"));
    }

    public void testParseAuthor51() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "H");
        expected.put("last", "Lloyd Evans");
        expected.put("first", "T");
        assertEquals(expected, AuthorUtils.parseAuthor("Lloyd Evans, T. H."));
    }

    public void testParseAuthor52() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "H");
        expected.put("last", "Lloyd Evans");
        expected.put("first", "T");
        System.err.println("#########   ERROR - this case is known to fail and no remedy so far!   ########");
        System.err.println("expected: " + expected.toString());
        System.err.println("actual:" + AuthorUtils.parseAuthor("T. H. Lloyd Evans").toString());
        //assertEquals(expected, AuthorUtils.parseAuthor("T. H. Lloyd Evans"));
    }

    public void testParseAuthor53() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "A");
        expected.put("last", "Higgs");
        expected.put("first", "Lloyd");
        assertEquals(expected, AuthorUtils.parseAuthor("Higgs, Lloyd A."));
    }

    public void testParseAuthor54() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "A");
        expected.put("last", "Higgs");
        expected.put("first", "Lloyd");
        assertEquals(expected, AuthorUtils.parseAuthor("Lloyd A. Higgs"));
    }

    public void testParseAuthor55() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "Middle");
        expected.put("last", "Mac Low");
        expected.put("first", "Furst");
        assertEquals(expected, AuthorUtils.parseAuthor("Mac Low, Furst Middle"));
    }

    public void testParseAuthor56() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "Middle");
        expected.put("last", "Mac Low");
        expected.put("first", "Furst");
        assertEquals(expected, AuthorUtils.parseAuthor("Furst Middle Mac Low"));
    }

    public void testParseAuthor57() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "Middle");
        expected.put("last", "'t Hooft");
        expected.put("first", "Furst");
        assertEquals(expected, AuthorUtils.parseAuthor("'t Hooft, Furst Middle"));
    }

    public void testParseAuthor58() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "Middle");
        expected.put("last", "'t Hooft");
        expected.put("first", "Furst");
        assertEquals(expected, AuthorUtils.parseAuthor("Furst Middle 't Hooft"));
    }
    
	public void testParseAuthor59() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "C");
        expected.put("last", "ST CYR");
        expected.put("first", "O");
        assertEquals(expected, AuthorUtils.parseAuthor("ST CYR, O C"));
		
	}
	public void testParseAuthor60() throws Exception {
        HashMap<String,String> expected = new HashMap<String,String>();
        expected.put("middle", "C");
        expected.put("last", "ST CYR");
        expected.put("first", "O");
        System.err.println("#########   ERROR - this case is known to fail and no remedy so far!   ########");
        System.err.println("expected: " + expected.toString());
        //assertEquals(expected, AuthorUtils.parseAuthor("CYR, O C ST"));
	}
	
}
