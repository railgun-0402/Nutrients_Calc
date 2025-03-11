# Nutrients_Calc
## 概要 
- PFC栄養素から、任意の食べ物の総カロリー量を計算するアプリです

## 設計方針
- 責務を分割し、どこに何が記されているか分かりやすくすること(可読性・開発生産性向上)
  - カロリー計算も様々なケースがありそうで、複数のケースが同じファイルに混ざるなど煩雑化防止
- ドメイン知識系のロジックはdomain層に記載する
- ユースケースはdomainを使用する手続部分にすることで、何の計算をしているか明確にする
  - ユースケースが多・ロジックは少ないイメージ

### 例
- カロリー計算ロジックを使用するケース
  - 運動による消費カロリー計算
  - 食事による摂取カロリー計算(今回はこれ)
  - 1日のトータルカロリー計算

## 悩んだところ
- カロリー計算ならば、食事による「摂取カロリー算出」や運動による「消費カロリー算出」等、似たようで様々なケースがあること
  - 「摂取量をカロリーに変換」というビジネスロジックはドメイン層に記載しました
  - カロリー変換を呼び出す・四捨五入など数学的共通知識はそれ以外の層に記載することにしました
  - これにより、ドメイン層ではビジネス上必要なロジックが共有しやすい運用になるかと考えました

## 各層の責務
### Main
- 今回はMain.javaからアプリケーションを実行しています
- 本来はUI系の処理を実施するPresentation層の役割として想定いただけますと幸いです

### application
- いわゆるユースケース層になります
  - 今回は摂取量を元に、カロリーを算出をするケースです
  - 他にも「消費カロリー算出」等、ユースケースを扱う想定です
- 詳細な計算ロジックはドメイン層に任せています

### domain
- 主にビジネスロジックに関わる部分になります
  - あすけんアプリにおいて、カロリー計算はビジネスの中心ロジックの1つと予想しました
  - PFC摂取量に基づいて、カロリーに変換しています
  - ケースは様々ありますが、カロリー算出として共通で利用可能です

### utils
- 他の層に依存しないユーティリティ関数を格納します
- 数学的な共通知識やバリデーション系の処理は、ここで共通ロジックとして扱います
  - 数値かどうか判断する
  - 四捨五入処理

### infrastructure
- 今回は作成していませんが、外部APIやDB連携が発生する処理があるかと思うのでその際は準備すると責務が分かりやすいかと思います

## クラスごとの説明
### Main.java
- 単純に計算メソッドを呼び出し、結果を出力します

### Calorie.java (Model)
- PFCそれぞれのカロリー数をenumで定義をしています

### PFC.java (Model)
- PFC摂取量を管理するレコードクラス

### FoodCalorieCalc.java (Application)
- 食品摂取量からカロリーを計算するクラス
- サービス層の計算メソッドを使用し、カロリーの変換を実施する

### CalorieCalcService.java (Service)
- カロリー変換、計算を実施します
- トータルのカロリー量を計算します
- 摂取量のバリデーションチェックをしています

### DecimalUtils.java (Utils)
- 四捨五入の計算は共通化の名目でUtilsクラスに記載しました
- 本来はアプリの入力から値を受け取ることも想定しました
  - 数値かどうかを判断する、バリデーションメソッドをUtilsクラスに実装しました
  - 摂取量(g)は基本正の数値なので、負の数や文字列等は受け付けないべきです
  - 入力されたことを想定して、テストケースに確認すべきケースを実装しました

## 使用技術
<img src="https://skillicons.dev/icons?i=java,gradle,github,githubactions" /> <br /><br />

## テスト
### 使用技術
- Junit5

### ケースの説明
- こちらはコメントに残してありますので、そちらをご参照いただけますと幸いです
- 基本は仕様を満たすことを確認しております
- カロリーや摂取量は正の数値なので、それ以外の値が来てしまったことを想定する異常系ケースも用意しています

## CI/CD
- github actionsで、Lintとテストコードのチェックを実施しています
- 本課題では必要ありませんが、実務を想定してCIのレポートを出力するようにしています
- トリガーは、mainブランチへのPR作成時とマージした際に実行される仕様です
