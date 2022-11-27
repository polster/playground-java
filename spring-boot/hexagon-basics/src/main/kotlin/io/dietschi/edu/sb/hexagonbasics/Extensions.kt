package io.dietschi.edu.sb.hexagonbasics

import java.util.*

fun <T> Optional<T>.unwrap(): T? = orElse(null)