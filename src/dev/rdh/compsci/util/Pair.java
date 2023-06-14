package dev.rdh.compsci.util;

import java.util.Objects;

public class Pair<F, S> {

	F first;
	S second;

	protected Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	public static <F, S> Pair<F, S> of(F first, S second) {
		return new Pair<>(first, second);
	}

	public F getFirst() {
		return first;
	}

	public S getSecond() {
		return second;
	}

	public void setFirst(F first) {
		this.first = first;
	}

	public void setSecond(S second) {
		this.second = second;
	}

	public Pair<F, S> copy() {
		return Pair.of(first, second);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == this)
			return true;
		if (obj instanceof Pair<?, ?> other) {
			return Objects.equals(first, other.first) && Objects.equals(second, other.second);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (Meth.nullHash(first) * 31) ^ Meth.nullHash(second);
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}

	public Pair<S, F> opposite() {
		return Pair.of(second, first);
	}
}
