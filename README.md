# LINE Notify Sample

OAuth認証をしてLINEにMessageを送るSample

### 手順1. LINE Notify( https://notify-bot.line.me/ja )にアクセスして、サービスを登録
Callback URLには `http://localhost:8080/api/_created-code` と登録してください

### 手順2. 設定ファイルを作成
- /src/main/resources/_sample.application.conf を application.conf としてコピーします
- ファイル中の <YOUR_CLIENT_ID> / <YOUR_CLIENT_SECRET> は手順1で登録したサービスの情報を設定して下さい
- databaseは使用していません

### 手順3. Skinny Framework を実行します

    ./skinny run

### 手順4. Webブラウザでアクセス

1. http://localhost:8080/ にアクセス
- ログイン画面っぽいものが表示されますが、適当に入力して[Sign in]ボタンをclickして下さい

2. [1. まず、LINEでログインして下さい]リンクをclickして、LINEのログイン画面を表示
- ログインして、LINE Notify と連携して下さい
- アプリの画面に戻ります

3. textにmessageを入力して[Send]ボタンをクリック
- textで入力したmessageが連携したアカウント(or グループ)へ表示されます


## 見るべき所
1. /src/main/scala/controller/LineAuthorizeController.scala
- LINEの認証画面へ遷移するURLですが、リダイレクト先等のパラメータを設定しています。

2. /src/main/scala/controller/api/AuthorizeController.scala
- LINEの認証が完了した後に呼ばれるエンドポイント(/api/_created-code)
- 現在はSession上にaccessTokenを保存していますが、本当は、このタイミングでユーザと紐付けて永続化することで何度も認証しなくてもよくなります

3. /src/main/scala/controller/api/SendController.scala
- メッセージを送るエンドポイント
- accessTokenがあれば送信できます
