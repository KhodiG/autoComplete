package autocomplete;

import java.util.Comparator;

public class Term implements Comparable<Term> {
	private final String query;
	private final double weight;

	// Initialize term object with query and weight
	public Term(String query, double weight) {
		if (query == null)
			throw new java.lang.IllegalArgumentException();
		if (weight < 0)
			throw new java.lang.IllegalArgumentException();
		this.query = query;
		this.weight = weight;
	}

	// Compares terms by weight in descending order
	public static Comparator<Term> byReverseWeightOrder() {
		return new ComparatorReverseWeight();

	}

	// Compares terms in lexicographic order only using the first n
	// characters of each query
	public static Comparator<Term> byPrefixOrder(int n) {
		if (n < 0)
			throw new java.lang.IllegalArgumentException();
		return new ComparatorPrefix(n);

	}

	// Compares terms by query in lexicographic order
	public int compareTo(Term that) {
		return this.query.compareTo(that.query);

	}

	// Returns string representation of the term
	public String toString() {
		return weight + "\t" + query;

	}

	// comparator for prefix order
	private static class ComparatorPrefix implements Comparator<Term> {

		private int r;

		private ComparatorPrefix(int r) {
			this.r = r;
		}

		@Override
		public int compare(Term a, Term b) {

			String A;
			String B;

			if (a.query.length() < r)
				A = a.query;
			else
				A = a.query.substring(0, r);

			if (b.query.length() < r)
				B = b.query;
			else
				B = b.query.substring(0, r);

			return A.compareTo(B);

		}

	}

	// Comparator for weight reverse order
	private static class ComparatorReverseWeight implements Comparator<Term> {

		@Override
		public int compare(Term a, Term b) {
			if (a.weight == b.weight)
				return 0;
			if (a.weight > b.weight)
				return -1;
			return 1;
		}

	}
}