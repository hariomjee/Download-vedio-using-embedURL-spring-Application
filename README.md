# Download-vedio-using-embedURl-spring-Application
Some websites does not allowed to download video directly. So here is a solution for that.

Here are some Steps you need to follows.
### 1. Open the vedio and go to it'ss inspect using right click on screen. And into element search for "embedURL"
   <img width="1434" alt="Screenshot 2023-12-12 at 1 30 42 PM" src="https://github.com/hariomjee/Download-vedio-using-embedURL-spring-Application/assets/81242043/c3b23efc-869f-47be-a3cc-f0b0a560c3c1">

### 2. Now copy this json data which includes this embedUrl. It's Like below.
      {"@context":"http://schema.org/","@id":"https://fast.wistia.net/embed/iframe/awo0j1eky7","@type":"VideoObject","duration":"PT36M40S","name":"Part1_1.mp4","thumbnailUrl":"https://embed-ssl.wistia.com/deliveries/0a7d03596dabb6f0482da83c73f694edfcafb5e5.jpg?image_crop_resized=1280x720","embedUrl":"https://fast.wistia.net/embed/iframe/awo0j1eky7","uploadDate":"2022-11-08T15:28:41.000Z","description":"a Learnworlds: apnacollegepage video","contentUrl":"https://embed-ssl.wistia.com/deliveries/0a0f1826d607224ab3c6a4ec6b0a2cabc2f2719a.m3u8","potentialAction":{"@type":"SeekToAction","target":"https://www.apnacollege.in/videoplayer?courseid=alpha-batch-2&videoid=63da7c9622ba5fd00c0634ef&courseslug=633ff6c5954dd329e90659b2&unit=63da7c9622ba5fd00c0634ef&wtime={seek_to_second_number}","startOffset-input":"required name=seek_to_second_number"}}

### 3. Now run your spring application. After start type http://localhost:8080/ into your browser.
### 4. Now inside input box paste above copy json data and click on button process.
   ![image](https://github.com/hariomjee/Download-vedio-using-embedURL-spring-Application/assets/81242043/d2c99386-2396-43ec-951d-830a056cfb59)

### 5. Vedio result will show there on click 3 dot you can see download button there.
   ![Screenshot 2023-12-13 at 12 35 37 PM](https://github.com/hariomjee/Download-vedio-using-embedURL-spring-Application/assets/81242043/9db72e07-9824-4775-83fd-6facc427f9a3)



### Thank you.
