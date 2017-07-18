package i_introduction._10_Object_Expressions

fun task10(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    arrayList.sortBy { -it }
    return arrayList
}
