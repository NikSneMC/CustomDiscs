# CustomDiscs
[README.md](https://github.com/NiKSneMC/CustomDiscs#readme)

[README_ENGLISH.md](https://github.com/NiKSneMC/CustomDiscs/blob/main/README_ENGLISH.md)

[Вернуться в главную директорию](https://github.com/NiKSneMC/CustomDiscs)

Что изменено?
-------------
* Все сообщения плагина в чате и в консоли (за исключением двух) переведены на русский язык
* Сообщения плагина в консоли стали цветными
* Комментарии в файле [`config.yml`](https://github.com/NiKSneMC/CustomDiscs/blob/main/src/main/resources/config.yml) переведены на русский язык
* Изменено сообщение об успешной загрузке плагина (p.s в оригинале была просто строчка `Successfully registered CustomDiscs plugin` белым цветом)
  ![image](https://user-images.githubusercontent.com/111704048/186025569-e668624b-5439-449e-9560-02f7fef73de2.png)
* Имена плагина в `/pl` (и аналогах этой команды) и папки плагина изменены на `CustomDiscs_Translated`

Оригинал
--------
Да да, это всего лишь перевод с некоторыми изменениями в сообщениях консоли

* **Автор оригинального плагина: [Navoei](https://github.com/Navoei)**
* **GitHub оригинального плагина: https://github.com/Navoei/CustomDiscs**
<details>
<summary>Оригинальный readme.md</summary>

Custom Discs 1.19.x
-------------------

A Paper fork of henkelmax's Audio Player.
* Play custom music discs using the Simple Voice Chat API. (The voice chat mod is required on the client and server.)
* Use `/customdisc` or `/cd` to create a custom disc.
* Music files should go into `plugins/CustomDiscs/musicdata/`
* Music files must be in the `.wav` or `.mp3` format.
* Only custom discs are compatible with hoppers.

Permission Node (Required to run the command. Playing discs does not require a permission.):

* `customdiscs.command`

Dependencies:
=======
* This plugin depends on the latest version of ProtocolLib for

https://user-images.githubusercontent.com/64107368/178426026-c454ac66-5133-4f3a-9af9-7f674e022423.mp4
</details>

Перевод оригинальной статьи(есть небольшие изменения в сторону корректности)
---------------------------
Paper форк Аудио Плеера Henkelmax`а
* Проигрывайте свою музыку, используя Simple Voice Chat API. (Мод на голосовой чат должен быть установлен на клиенте, а плагин на сервере)
* Используйте команду `/customdisc` или `/cd` для создания своей пластинки
* Файлы аудиозаписей должны лежать в папке `plugins/CustomDiscs_Translated/musicdata/`
* Файлы аудиозаписей должны быть формата `.wav` или `.mp3`.
* Только пользовательские пластинки работают с воронками.

Разрешения плагина (требуется для выполнения команды. Для воспроизведения дисков не требуется разрешение.):
* `customdiscs.command`

Требуемые плагины:
* [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/)
* [Simple Voice Chat](https://www.curseforge.com/minecraft/bukkit-plugins/simple-voice-chat/files/all)

https://user-images.githubusercontent.com/111704048/186036791-9388be12-939c-41eb-b6cf-7161ab4ec4ff.mp4

Как установить на сервер?
-------------------------
* Скачать необходимую версию с [этой](https://github.com/NiKSneMC/CustomDiscs/releases) страницы
* Скачать подходящую версию [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/) (ссылка на spigotmc.org)
* Скачать подходящую версию [Simple Voice Chat](https://www.curseforge.com/minecraft/bukkit-plugins/simple-voice-chat/files/all) (ссылка на CurseForge.com)
* Перекинуть скачанные плагины в папку `plugins` вашего сервера
* перезапустить сервер

Как собрать вручную?
--------------------
**ВАЖНО!!! Эта статья написана чисто мной, к автору оригинального плагина она никакого отношения не имеет!**


**Я крайне рекомендую собирать плагин в [Intellij IDEA](https://www.jetbrains.com/ru-ru/idea/). Если вы знаете, как это делать с помощью другой программы, то делайте в ней.**
* Установить [Intellij IDEA](https://www.jetbrains.com/ru-ru/idea/), если он у вас не усановлен
* Скачать `Source code (zip)` или `Source code (tar.gz)` архив с нужного релиза
* Распаковать скачанный архив  в папку проектов Intellij IDEA (в IntelliJ IDEA Community Edition путь по умолчанию `C:\Users\<Ваш пользователь>\IdeaProjects`)
* В меню создания нового проекта нажмите <kbd>Open</kbd> () и в открывшемся окне выберите только что распакованную папку
* Ожидайте полной загрузки проекта

**После предыдущего этапа можно изменять файлы**

* Откройте вкладку <kbd>Gradle</kbd> (показана на картинке ниже) и нажмите на кнопку ![image](https://user-images.githubusercontent.com/111704048/186053563-1b693c0e-0c7d-458d-850d-2434ec770af5.png)

![image](https://user-images.githubusercontent.com/111704048/186021694-9c49575d-b758-47e9-9ddc-1e5178f4d32e.png)
* После этого в папке ```Customdiscs/Tasks/build``` запустите файл ```build``` (скриншот ниже)

![image](https://user-images.githubusercontent.com/111704048/186022904-caf31c7e-1b33-4d88-8522-a5e482995ecc.png)
* По окончании компиляции в проводнике открываем папку проекта, после чего переходим в ```build\libs```
* Открываем ```.jar``` файл в любом архиваторе и копируем в него содержимое папки ```to drop into .jar```(она находится в папке с самим ```.jar``` файлом)
* После того как всё скопируется в архив, можно закрывать архиватор. На этом сборка плагина заканчивается