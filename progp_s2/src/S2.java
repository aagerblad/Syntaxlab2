import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class S2 {

	public static void main(String[] args) throws IOException {
		File input = new File("src/dr.txt");
		Scanner s = new Scanner(input);
		while (s.hasNext()) {
			System.out.println(new S2().eval(s.nextLine()));
			
		}
	}
	
	private Stack<String> symbols;
	private int val;

	public int eval(String str) {
		// split strings on whitespace and push on stack in reverse order
		// (we want the last word to be at the bottom of the stack, i.e. be
		// pushed first)
		List<String> subStrs = Arrays.asList(str.split("\\s+"));
		Collections.reverse(subStrs);
		symbols = new Stack<String>();
		symbols.addAll(subStrs);

		// value should initially be 0
		val = 0;

		// let dansk take care of the conversion
		dansk();

		// returned value which dansk has adjusted
		return val;
	}

	/**
	 * Tries to peek the string on top of the symbols stack. Returns an empty
	 * string if the stack contains no elements.
	 * 
	 * @return the peek of the symbols stack, an empty string if stack is empty.
	 */
	private String peek() {
		try {
			return symbols.peek();
		} catch (EmptyStackException e) {
			return "";
		}
	}

	private void dansk() {
		switch (peek()) {
		case "nul":
			val = 0;
			break;
		default:
			en2ni();
		}
	}

	// @formatter:off
	private void en2ni() {
		switch(peek()) {
		case "et": 		val += 1;	mer();	break;
		case "en": 		val += 1;	mer();	break;
		case "to": 		val += 2;	mer();	break;
		case "tre": 	val += 3;	mer();	break;
		case "fire": 	val += 4;	mer();	break;
		case "fem": 	val += 5;	mer();	break;
		case "seks": 	val += 6;	mer();	break;
		case "syv": 	val += 7;	mer();	break;
		case "otte":	val += 8;	mer();	break;
		case "ni": 		val += 9;	mer();	break;
		default:		
			ti2nitten();
		}
	}

	private void ti2nitten() {
		switch(peek()) {
		case "ti": 		val = 10;	break;
		case "ellve": 	val = 11;	break;
		case "tolv": 	val = 12;	break;
		case "tretten": val = 13;	break;
		case "fjorten":	val = 14;	break;
		case "femten": 	val = 15;	break;
		case "seksten":	val = 16;	break;
		case "sytten": 	val = 17;	break;
		case "atten":	val = 18;	break;
		case "nitten":	val = 19;	break;
		default: 
			tyve2halvfems();
		}
	}
	// @formatter:on

	private void mer() {
		switch (peek()) {
		case "og":
			tyve2halvfems();
			break;
		}
	}

	// @formatter:off
	private void tyve2halvfems() {
		switch(peek()) {
		case "tyve": 		val += 20;	break;
		case "tredive": 	val += 30;	break;
		case "fyrre": 		val += 40;	break;
		case "fyrretyve": 	val += 40;	break;
		default:			
			halvtreds2halvfems();
			emfas();		
		}
	}
	// @formatter:on

	private void halvtreds2halvfems() {
		switch (symbols.peek()) {
		case "halv":
			val -= 10;
			halvmult();
			break;
		default:
			mult();
		}
	}

	// @formatter:off
	private void mult() {
		switch(peek()) {
		case "tres": 	val += 60;	break;
		case "firs": 	val += 80;	break;
		case "fjerds": 	val += 80;	break;		
		}
	}
	// @formatter:on

	private void halvmult() {
		switch (peek()) {
		case "fems":
			val += 100;
			break;
		default:
			mult();
		}
	}

	private void emfas() {
		// do nothing, adjusted by letting tres be 60, firs 80, etc. and halv be
		// -10.
	}

}

// //dansk → nul
// //| en2ni mer
// //en2ni → en mer
// //| et mer
// //| to mer
// //| tre mer
// //| fire mer
// //| fem mer
// //| seks mer
// //| syv mer
// //| otte mer
// //| ni mer
// //| ti2nitten
// //ti2nitten → ti
// //| ellve
// //| tolv
// //| tretten
// //| fjorten
// //| femten
// //| seksten
// //| sytten
// //| atten
// //| nitten
// //| tyve2halvfems
// //mer → og tyve2halvfems (+ tiotal t, 20 ≤ t ≤ 90)
// //| ε ( 0, dvs enbart en2ni)
// //tyve2halvfems → tyve (20)
// //| tredive (30)
// //| fyrre (40)
// //| fyrretyve (40)
// //| halvtreds2halvfems emfas (tiotal t, 50 ≤ t ≤ 90)
// //halvtreds2halvfems → halv halvmult (-0,5+...)
// //| mult (...)
// //mult → tres 3)
// //| firs 4)
// //| fjerds 4)
// //halvmult → mult
// //| fems 5)
// //emfas → sindtyve (× 20)
// //| ε (× 20)