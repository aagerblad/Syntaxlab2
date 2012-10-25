import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class S2 {
	
//	class EndSym {
//		String sym;
//		Integer val;
//		
//		EndSym(String sym, Integer val) {
//			this.sym = sym;
//			this.val = val;
//		}
//	}
	
	private Stack<String> symbols;
	private int val;
	
	public int eval(String str) {
		List<String> subStrs = Arrays.asList(str.split("\\s+"));
		Collections.reverse(subStrs);
		symbols = new Stack<String>();
		symbols.addAll(subStrs);
		
//		for(int i = subStrs.length; i >= 0; i--) {
//			symbols.push(subStrs[i]));
//		}		
	}
	
	private void popAndAssign(int val) {
		symbols.pop().val = val;
	}
	
	private void dansk() {
		String es = symbols.peek();
		
		if (es.sym.equals("nul"))
			es.val = 0;
		else
			en2ni();
	}
	
	private void en2ni() {
		EndSym es = symbols.peek();
		
		if (es.sym.equals("en"))
			symbols.pop();
			es.val = 1;
			mer();
		else if (es.sym.equals("et"))
			es.val = 1;
		else if (es.sym.equals("to"))
			es.val = 2;
		else if (es.sym.equals("tre"))
			es.val = 3;
		else if (es.sym.equals("fire"))
			es.val = 4;
		else if (es.sym.equals("fem"))
			es.val = 5;
		else if (es.sym.equals("seks"))
			es.val = 6;
		else if (es.sym.equals("syv"))
			es.val = 7;
		else if (es.sym.equals("otte"))
			es.val = 8;
		else if (es.sym.equals("ni"))
			es.val = 9;
		else
			ti2nitten();
	}
	
	private void ti2nitten() {
		EndSym es = symbols.peek();
		
		if (es.sym.equals("ti"))
			es.val = 10;
		else if (es.sym.equals("ellve"))
			es.val = 11;
		else if (es.sym.equals("tolv"))
			es.val = 12;
		else if (es.sym.equals("tretten"))
			es.val = 13;
		else if (es.sym.equals("fjorten"))
			es.val = 14;
		else if (es.sym.equals("femten"))
			es.val = 15;
		else if (es.sym.equals("seksten"))
			es.val = 16;
		else if (es.sym.equals("sytten"))
			es.val = 17;
		else if (es.sym.equals("atten"))
			es.val = 18;
		else if (es.sym.equals("nitten"))
			es.val = 19;
		else
			tyve2halvfems();
	}
	
	
	
	
}

//dansk	→	nul
//|	en2ni	mer
//en2ni	→	en	mer
//|	et	mer
//|	to	mer
//|	tre	mer
//|	fire	mer
//|	fem	mer
//|	seks	mer
//|	syv	mer
//|	otte	mer
//|	ni	mer
//|	ti2nitten
//ti2nitten	→	ti
//|	ellve
//|	tolv
//|	tretten
//|	fjorten
//|	femten
//|	seksten
//|	sytten
//|	atten
//|	nitten
//|	tyve2halvfems
//mer	 →	og tyve2halvfems	(+ tiotal t, 20 ≤ t ≤ 90)
//|	ε	( 0, dvs enbart en2ni)
//tyve2halvfems	 →	tyve	(20)
//|	tredive	(30)
//|	fyrre	(40)
//|	fyrretyve	(40)
//|	halvtreds2halvfems emfas	(tiotal t, 50 ≤ t ≤ 90)
//halvtreds2halvfems	→	halv halvmult	(-0,5+...)
//|	 mult	(...)
//mult	 →	tres	3)
//|	firs	4)
//|	fjerds	4)
//halvmult	 →	mult	
//|	fems	5)
//emfas	→	sindtyve	(× 20)
//|	 ε	(× 20)