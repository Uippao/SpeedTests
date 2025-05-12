using System;
using System.Diagnostics;

class Program {
    static void Main() {
        var sw = Stopwatch.StartNew();
        for (int i = 1; i <= 10000; i++) Console.WriteLine(i);
        sw.Stop();
        Console.WriteLine($"Elapsed: {sw.Elapsed.TotalSeconds:F9} seconds");
    }
}
