import java.io.File
import java.io.InputStream
import java.util.*

//adds to database if data is in a list
fun addListText(fileName: String, data: List<String>){

    //takes file path as a string
    val myFile = File(fileName + ".txt")
    var lines = countLine(fileName)

    //starts new line
    myFile.appendText("\n")
    //finds ID for the new line using the amount of lines in the file
    myFile.appendText(lines.toString() + ", ")

    //data that has been passed from the GUI is added to databse
    for(x in 0..data.size-1){
        myFile.appendText(data.elementAt(x) + ", ")
    }

}

//adds to database if single variable
fun addVariableText(fileName: String, data: String){
    //same as above but if only a single item has been passed
    val myFile = File(fileName + ".txt")
    var lines = countLine(fileName)
    myFile.appendText("\n")
    myFile.appendText(lines.toString() + ", ")
    myFile.appendText(data)

}

//reads from database
fun readText(fileName: String):List<String>{
    //takes filename into input stream
    val inputStream: InputStream = File(fileName + ".txt").inputStream()
    val lineList = mutableListOf<String>()

    //adds each line to a list
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }

    return lineList

}

//counts lines in database
fun countLine(fileName: String):Int{
    var count = 0
    val myFile = File(fileName + ".txt")
    val sc = Scanner(myFile)
    //reads file and counts each line to get total number of lines in the database
    while(sc.hasNextLine()) {
        sc.nextLine();
        count = count+1
    }
    return count
}
