# Java 7 Features
Java 7 introduced several new features and enhancements aimed at improving the developer experience and the performance of Java applications. Here are some of the key features:

## Language Enhancements

### Diamond Operator

The diamond operator (<>) simplifies the use of generics by allowing the compiler to infer the type parameters. For example:
```java
Map<String, List<Trade>> trades = new TreeMap<>();
```
This eliminates the need to specify the type parameters on both sides of the declaration.

### Strings in Switch Statements

Java 7 allows the use of strings in switch statements, making the code more readable and concise. For example:
```java
switch(status) {
case "NEW":
newTrade(t);
break;
case "EXECUTE":
executeTrade(t);
break;
case "PENDING":
pendingTrade(t);
break;
default:
break;
}
```
This feature uses the String.equals() method for comparison.

### Try-With-Resources

The try-with-resources statement simplifies resource management by automatically closing resources at the end of the statement. For example:
```java
try (FileOutputStream fos = new FileOutputStream("movies.txt");
DataOutputStream dos = new DataOutputStream(fos)) {
dos.writeUTF("Java 7 Block Buster");
} catch (IOException e) {
e.printStackTrace();
}
```
Resources must implement the AutoCloseable interface

### Multi-Catch Exception Handling

Java 7 allows multiple exceptions to be caught in a single catch block using the | operator. For example:
```java
try {
methodThatThrowsThreeExceptions();
} catch (ExceptionOne | ExceptionTwo | ExceptionThree e) {
e.printStackTrace();
}
```
This reduces code duplication and improves readability


## New File System API (NIO 2.0)

Java 7 introduced a new file system API that provides better support for file operations, including symbolic links and file change notifications. For example, to watch a directory for changes:
```java
WatchService watchService = FileSystems.getDefault().newWatchService();
Path path = Paths.get("C:\\Temp\\temp\\");
path.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

while (true) {
WatchKey key = watchService.take();
for (WatchEvent<?> event : key.pollEvents()) {
System.out.println("Event on " + event.context() + " is " + event.kind());
}
key.reset();
}
```
This API makes it easier to work with files across different operating systems.

## Fork and Join Framework

The fork and join framework is designed for parallel processing by breaking tasks into smaller subtasks. It uses a work-stealing algorithm to efficiently utilize available processors. For example:
```java
ForkJoinPool pool = new ForkJoinPool();
pool.invoke(new RecursiveAction() {
@Override
protected void compute() {
// Task implementation
}
});
```
This framework helps in leveraging multi-core processors for better performance.

## Support for Dynamically-Typed Languages

Java 7 introduced the invokedynamic bytecode instruction to improve the performance of dynamically-typed languages on the JVM. This feature is part of the java.lang.invoke package and includes classes like MethodHandle and CallSite.

These features and enhancements in Java 7 aim to make the language more powerful, expressive, and efficient for developers.
