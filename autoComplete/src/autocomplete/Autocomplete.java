package autocomplete;

import java.util.Arrays;

public class Autocomplete {
	private final Term[] terms;

	public Autocomplete(Term[] terms) {
		if (terms == null)
			throw new java.lang.NullPointerException();
		this.terms = new Term[terms.length];
		for (int i = 0; i < terms.length; i++) {
			this.terms[i] = terms[i];
		}
		Arrays.sort(this.terms);
	}

	// Returns terms that start with the prefix
	public Term[] allMatches(String prefix) {
		if (prefix == null)
			throw new java.lang.NullPointerException();

		int first = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		if (first == -1)
			return new Term[0];

		int last = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

		Term[] match = new Term[1 + last - first];

		for (int i = 0; i < match.length; i++) {
			match[i] = terms[first++];
		}
		Arrays.sort(match, Term.byReverseWeightOrder());

		return match;
	}

	// Returns number of terms that start with the prefix.
	public int numberOfMatches(String prefix) {
		if (prefix == null)
			throw new java.lang.NullPointerException();
		return 1 + BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()))
				- BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));

	}
}