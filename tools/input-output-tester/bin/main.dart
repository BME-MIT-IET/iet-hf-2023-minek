
import 'dart:convert';
import 'dart:io';

import 'package:archive/archive_io.dart';

void main(List<String> arguments) async {
    
    final path = "./tests";
    final testFolder = Directory(path);
    if(!testFolder.existsSync()){
        print("\"tests\" folder not found beside executable. Please create it and place the test files inside.");
        exit(1);
    }
    final commandFile = File("./command.txt");
    var command = "";
    if(commandFile.existsSync()){
        command = commandFile.readAsStringSync();
    }
    else{
        print("Please enter the command to run the main class of the game:");
        command = stdin.readLineSync()!;
    }

    final compressed = File("./tests/tests.zip");
    if(compressed.existsSync()){
        extractFileToDisk('./tests/tests.zip', './tests/');
    }

    final testFiles = testFolder.listSync();
    var passed = 0;
    var failed = 0;

    for(var file in testFiles){
        if(file is File && file.path.endsWith(".test")){
            final lines = file.readAsLinesSync();
            var input = "";
            var expectedOutput = "";
            var status = 0;
            for(var line in lines){
                if(line.isEmpty)continue;
                if(line.startsWith("[input]")){
                    status = 1;
                    continue;
                }
                else if (line.startsWith("[expected]")){
                    status = 2;
                    continue;
                }
                if(status == 1){
                    input += line + "\n";
                }
                else if(status == 2){
                    expectedOutput += line + "\n";
                }
            }
            

            var p = await Process.start(command,[],workingDirectory: "./", runInShell: true);
            p.stdin.write(input);
            p.stdin.close();
            var outputs = await p.stdout.transform(utf8.decoder).toList();
            //outputs = outputs.map((line) => line.replaceAll(RegExp(r'@[a-zA-Z0-9]+'), "").replaceAll("[]", "null")).toList();
            final output = outputs.join("");
            if(!output.contains(expectedOutput)){
                print("\x1B[31mTest failed: ${file.path}\x1B[0m");
                print("Expected: \n$expectedOutput");
                print("Got: \n$output");
                failed++;
            }
            else{
                print("\x1B[32mTest passed: ${file.path}\x1B[0m");
                passed++;
            }
        }
    }
    print("${passed+failed} tests executed, \x1B[32m$passed passed\x1B[0m, \x1B[31m$failed failed\x1B[0m.");
    stdin.readLineSync();
}
