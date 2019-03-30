# GCLogParser
JVMが出力するGCログの情報を整理して表示します。

## 実行方法
Main.javaをコンパイルして実行してください。

## 使用方法
GCにかかる時間を測定したいプログラムを実行するときに、`-verbose:gc -Xloggc:ログファイルを保存する場所`と指定して、GCのログファイルを保存し、
このプログラムを実行してログファイルのパスを入力すると、GCにかかった平均時間や回数等が表示されます。
