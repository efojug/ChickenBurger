package SD;

import sun.misc.Unsafe
import java.lang.Exception

private val unsafe: Unsafe by lazy {
    Unsafe::class.java.getDeclaredField("theUnsafe").let {
        it.isAccessible = true
        it[null] as Unsafe
    }
}

fun exitJava(e: Int): Nothing {
    try {
        unsafe.putAddress(0, 0)
    }catch (e: Exception) {
    }
    Runtime.getRuntime().exit(e)
    throw Error().also { it.stackTrace = arrayOf() }
}