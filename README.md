FlashCardApp
FlashCardApp-д тавтай морил! Энэхүү Android аппликэйшн нь хэрэглэгчдэд шинэ үгсийг flashcard-аар дамжуулан сурахад туслах зорилготой. Доор апп хэрхэн ажилладаг, архитектур болон эхлүүлэх зааврын талаар дэлгэрэнгүй тайлбарласан болно.
Агуулгын жагсаалт

Тойм
Архитектур

MVVM Pattern
Room Database
Jetpack Compose for UI
WorkManager & AlarmManager


Төслийн бүтэц
Хэрхэн ажилладаг вэ

MainActivity
ViewModel
Repository
Database болон DAO
UI Components
Мэдэгдэл

Тойм
FlashCardApp нь шинэ үгсийг нэмэх, засах, устгах, давтах боломжтой Android апп юм. Энэ нь үг болон орчуулгыг удирдахад хялбар, сонирхолтой хэрэглэгчийн интерфэйстэй. Мөн үг давтах цагийг сануулах мэдэгдэл илгээдэг.
Архитектур
MVVM Pattern

Model: Үгсийн мэдээллийг агуулсан өгөгдлийн классууд (жишээ нь Word класс).
ViewModel: UI-тай холбоотой өгөгдлийг удирдаж, Repository-тай харилцан ажиллаж, UI-г шинэчилнэ. (WordViewModel.kt-ийг үзнэ үү.)
View: Jetpack Compose ашиглан бүтээсэн. UI бүрдүүлэлтийн хэсэг нь үгсийг харуулдаг, засварлах dialog-ууд болон дэлгэц хоорондын navigation-ийг багтаана. (ui фолдер доторх файлууд.)

Room Database
Room ашиглан таны төхөөрөмж дээр flashcard-уудыг локал хадгалдаг.
WordDB нь өгөгдлийн баазыг үүсгэх, хувиргах ажлыг хариуцдаг, харин WordAppDao нь мэдээллийн үйлдлүүдийг (CRUD) гүйцэтгэнэ.
Jetpack Compose for UI
Бүх UI нь Jetpack Compose ашиглан бүтээгдсэн.
Үндсэн дэлгэц, тохиргооны дэлгэц, дуртай үгсийн дэлгэц зэрэг нь composable function хэлбэрээр бүтээгдсэн.
WorkManager & AlarmManager

WorkManager: Хэрэглэгчдэд өдөр бүр үг давтахыг сануулах тогтмол мэдэгдэл үүсгэдэг.
AlarmManager: WorkManager ажиллахгүй тохиолдолд мэдэгдэл заавал ирэх нөхцөлийг хангадаг нөөц механизм юм.

Төслийн бүтэц

ViewModel: ViewModel/WordViewModel.kt-д байрладаг. UI өгөгдлийг удирдаж, Repository-тай харилцана.
Repository: Repository/WordRepository.kt-д байрладаг. Өгөгдлийн үйлдлүүдийг төвлөрүүлж удирддаг.
Database: room/WordDB.kt дотор Room database үүсгэгдсэн. WordAppDao.kt нь DAO, харин Word.kt нь өгөгдлийн модел юм.
UI Components: Үүнд дэлгэцүүд (MainScreen.kt, SettingsScreen.kt, FavoritesScreen.kt) болон жижиг хэсгүүд (WordDisplay, WordEditDialog) орно.
Notifications: DailyNotificationWorker.kt болон нэмэлт helper классууд ашиглан мэдэгдэл үүсгэнэ.

Хэрхэн ажилладаг вэ
MainActivity

Апп-ын гол оролтын цэг.
Database, Repository, ViewModel-ийг эхлүүлнэ.
Compose-ийн NavHost ашиглан дэлгэц хооронд navigation хийдэг.
App хаагдах үед NotificationHelper-ийг дуудаж мэдэгдлийг төлөвлөнө.

ViewModel

WordViewModel.kt нь UI болон Repository-гийн хооронд холбогч үүрэгтэй.
Үг нэмэх, засах, устгах функцуудыг агуулна.
Kotlin coroutines ашиглан асинхрон өгөгдлийн үйлдлүүдийг гүйцэтгэнэ.

Repository

WordRepository.kt нь ViewModel болон Room database хоорондын зуучлагч юм.
CRUD үйлдлүүдийг төвлөрүүлж өгдөг.

Database болон DAO

Database (WordDB.kt): Room database үүсгэх ба хувиргалт хийнэ.
DAO (WordAppDao.kt): Үгсийг нэмэх, устгах, хайх, засах функцуудыг агуулна.

UI Components

MainScreen: WordDisplay ашиглан үгсийг харуулна.
WordEditDialog: Хэрэглэгчдэд үг нэмэх болон засах dialog.
WordDisplay: Үг болон орчуулгыг агуулсан flashcard-ийг харуулна.
Navigation Buttons: Flashcard хооронд шилжих товчлуурууд.
Settings Screen: Үгсийг хэрхэн үзүүлэхийг тохируулах хэсэг.
Favorites Screen: Дуртайгаар тэмдэглэсэн үгсийг жагсаана.

Мэдэгдэл

DailyNotificationWorker.kt: Мэдэгдлийн үндсэн логик.
NotificationHelper.kt ба NotificationReceiver.kt: WorkManager болон AlarmManager ашиглан мэдэгдлийг үүсгэх, ажиллуулах.
Хэрэглэгчдэд өдөр тутмын сануулга илгээдэг.
