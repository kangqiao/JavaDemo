class Test {

    fun test() {
val runnableFoo = object : Foo(), Runnable {
    override fun run() {
    }
}
    }
}

open class Foo{
    fun bar():Int {
        return 0;
    }
}