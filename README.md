# InfinitePager

![Peek 2021-09-28 21-50](https://user-images.githubusercontent.com/88603424/135145316-8d1dbf1a-19e7-4053-91ac-d549994918fa.gif)

#

[![](https://jitpack.io/v/mahdidev78/infinitepager.svg)](https://jitpack.io/#mahdidev78/infinitepager)
![GitHub repo size](https://img.shields.io/github/repo-size/mahdidev78/infinitepager)
![GitHub language count](https://img.shields.io/github/languages/count/mahdidev78/infinitepager)
![GitHub top language](https://img.shields.io/github/languages/top/mahdidev78/infinitepager)
![GitHub last commit](https://img.shields.io/github/last-commit/mahdidev78/infinitepager?color=red)

A simple android infinite view pager

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#download">Download</a></li>
        <li><a href="#usage">Usage</a></li>
      </ul>
    </li>
    <li><a href="#compatibility">Compatibility</a></li>
    <li><a href="#stats">Stats</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- GETTING STARTED -->
## Getting Started

### Download

Download the latest AAR from jitpack via Gradle:

<ul>
  <li>
Gradle

Project build.gradle
  
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
app module build.gradle

```gradle
dependencies {
  implementation 'com.github.mahdidev78:infinitepager:TAG'
}
```
    
  </li>
  <li>
Maven
    
Add the JitPack repository to your build file
    
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```
Add the dependency
```xml
<dependency>
  <groupId>com.github.mahdidev78</groupId>
  <artifactId>infinitepager</artifactId>
  <version>Tag</version>
</dependency>
```
  </li>
</ul>

<!-- USAGE EXAMPLES -->
## Usage

### Step 1

Add the ScrollToTop to your layout :

```xml
<com.mahdikh.vision.infinitepager.widget.InfinitePager
  android:id="@+id/infinitePager"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:clipToPadding="false"
  android:paddingStart="40dp"
  android:paddingEnd="40dp"
  app:currentItem="first"/>
```
### Step 2

Setup your code : 
<ul>
  <li>
    Kotlin
    
```kotlin
val pager = findViewById<InfinitePager>(R.id.infinitePager)
pager.adapter = adapter // extends InfiniteAdapter 
```
  </li>
  <li>
    Java
    
```java
InfinitePager pager = findViewById(R.id.infinitePager);
pager.setAdapter(adapter); extends InfiniteAdapter
```
  </li>
</ul>

### Advanced Step 3

<ul>
  <li>
  kotlin

```kotlin
val callback: Callback = object : Callback {
  override fun onPageScrolled(
      position: Int,
      positionOffset: Float,
      positionOffsetPixels: Int
  ) {
  }

  override fun onPageSelected(position: Int) {

  }
  override fun onPageScrollStateChanged(state: Int) {

  }
}
pager.registerCallback(callback)  // for register callback
pager.unregisterCallback(callback)  // for unregister callback
pager.unregisterCallbacks()  // unregister all calbacks
```
  </li>
  <li>
  Java

```java
Callback callback = new Callback() {
  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

  }

  @Override
  public void onPageSelected(int position) {

  }

  @Override
  public void onPageScrollStateChanged(int state) {

  }
};
pager.registerCallback(callback); // for register callback
pager.unregisterCallback(callback); // for unregister callback    
pager.unregisterCallbacks();  // unregister all calbacks

```
  </li>
</ul>

## Attributes

| attribute | Description | Options(examples)|
| --- | --- | --- |
| currentItem | set the current item | first, center, last , 0, 1 , etc |

<!-- _For more examples, please refer to the [Documentation](https://example.com)_ -->

## Compatibility

- Android KitKat 4.4+

## Stats

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![Apache License][license-shield]][license-url]

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- LICENSE -->
## License

    Copyright 2021, mahdidev78

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

<!-- CONTACT -->
## Contact

Mahdi Khosravi - mahdi.khosravi.dev78@gmail.com

Project Link: [https://github.com/mahdidev78/infinitepager](https://github.com/mahdidev78/infinitepager)


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/mahdidev78/InfinitePager.svg?
[contributors-url]: https://github.com/mahdidev78/infinitepager/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/mahdidev78/InfinitePager.svg?
[forks-url]: https://github.com/mahdidev78/infinitepager/network/members
[stars-shield]: https://img.shields.io/github/stars/mahdidev78/InfinitePager.svg?
[stars-url]: https://github.com/mahdidev78/infinitepager/stargazers
[issues-shield]: https://img.shields.io/github/issues/mahdidev78/InfinitePager.svg?
[issues-url]: https://github.com/mahdidev78/infinitepager/issues
[license-shield]: https://img.shields.io/github/license/mahdidev78/InfinitePager.svg?
[license-url]: https://github.com/mahdidev78/infinitepager/blob/master/LICENSE.txt
[product-screenshot]: images/screenshot.png
