<template>
  <div class="content">
    <!-- <uni-nav-bar left-icon="back" left-text="返回" title="相册"> -->
    <div class="headBox">
      <div class="headLeft" @click="goback()">返回</div>
      <div class="headMiddle">{{this.$root.$children[0].album_name}}</div>
      <div class="rightBox" slot="right">
        <img
          @click="handleDownload"
          class="icon icon1"
          src="../assets/i1.png"
        />
        <img @click="handleDelete" class="icon icon2" src="../assets/i2.png" />
      </div>
    </div>
    <!-- </uni-nav-bar> -->

    <div class="middle">
      <div style="height: 50px"></div>
      <van-checkbox-group v-model="checkedArr">
        <div class="itemBox">
          <div :key="index" v-for="(item, index) in imgList" class="item">
            <div class="itemImgBox">
              <!-- <img
                @click="predivImg(item.src)"
                class="itemImg"
                :src="item.src"
              /> -->
              <van-image
                @click="predivImg(item.url)"
                class="itemImg"
                :src="item.url"
                fit="fill"
              />
              <div v-if="editBol" class="itemImgCheckbox">
                <van-checkbox :name="index"></van-checkbox>
              </div>
            </div>
            <div class="itemBottom">
              <div class="itemName">{{ item.name }}</div>
              <div class="itemTime">{{ item.time }}</div>
            </div>
          </div>

          <div
            v-if="imgList.length % 2 !== 0"
            style="opacity: 0"
            class="item"
          ></div>
        </div>
      </van-checkbox-group>
      <!-- </van-checkbox-group> -->
    </div>

    <div class="bottom">
      <van-button @click="handleEnter"  :disabled="!editBol" type="primary"
        >确定</van-button
      >

      <van-button @click="handleCancel" :disabled="!editBol" type="danger"
        >取消</van-button
      >
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import Cookies from 'js-cookie';
import { CheckboxGroup, Checkbox, Button, Image, ImagePreview } from "vant";

Vue.use(CheckboxGroup);
Vue.use(Checkbox);
Vue.use(Button);
Vue.use(Image);

export default {
	name:"Photo",
  data() {
    return {
	  imgList:this.$root.$children[0].album_click,
      editBol: false,
      checkedArr: [],
    };
  },
  onLoad() {},
  methods: {
	  goback:function(){
		  this.$root.$children[0].conName="Main";
	  },
    predivImg(src) {
      if (this.editBol) {
        return;
      }
      ImagePreview([src]);
    },
    handleDownload() {
      this.editBol = "DOWNLOAD";
    },
    handleDelete() {
		
      this.editBol = "DELETE";
	  
    },
    handleEnter() {
      if (this.editBol == "DELETE") {
		   var vue = this;
		 		  
        this.imgList = this.imgList.filter((v, i) => {
			//这里的代码是在遍历 v是当前对象 i是索引
			//checkedArr  选中的张片
		 //    console.log("slected==",this.checkedArr.indexOf(i));
			// console.log("checkedArr==",this.checkedArr);
			// console.log("v i==",v,i);
			if(this.checkedArr.indexOf(i)>=0){
				console.log("v.id==",v.id);
				this.$root.axios.get("/server/user/deletePhoto",{
						 		  	params:{
						 		  		id:v.id
						 		  	}
						 		  }).then(function(res){
						 		  	console.log(res)
						 		  	if(res.data.code==101){
						 
						 		  	}else{
						 		  		console.log("error token")
						 		  	}
						 		  }).catch(function(err){
						 		  	console.log(err)
						 		  })
			}
		 return this.checkedArr.indexOf(i) == -1;
        });
		this.$root.axios.get("/server/user/refresh",{
			params:{
				username:vue.$root.$children[0].username
			}
		}).then(function(res){
			vue.$root.$children[0].albums=res.data.data.albums;
			var albumsStr=JSON.stringify(res.data.data.albums);
			Cookies.set("albums",albumsStr);
			console.log(res)
		}).catch(function(err){
			console.log(err)
		});
      } else {
        let newArr = [];
        this.checkedArr.forEach((v) => {
          newArr.push(this.imgList[v]);
        });
        for (var i = 0; i < newArr.length; i++) {
          this.download(newArr[i].url);
        }
      }

      this.editBol = false;
    },
    handleCancel() {
      this.editBol = false;
    },
    download(src) {
      var a = document.createElement("a");
      a.setAttribute("href", src); //图片地址
      a.setAttribute("download", "img1"); //下载时文件名称
      var evobj = document.createEvent("MouseEvents");
      evobj.initMouseEvent(
        "click",
        true,
        true,
        window,
        0,
        0,
        0,
        0,
        0,
        false,
        false,
        true,
        false,
        0,
        null
      );
      a.dispatchEvent(evobj);
    },
  },
};
</script>

<style>
* {
  box-sizing: border-box;
}
.headBox {
  position: fixed;
  left: 0;
  top: 0;
  height: 44px;
  background: #fff;
  width: 100%;
  z-index: 10;
  border-bottom: 1px solid #eee;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
  display: flex;
  align-items: center;
}
.headLeft {
  flex: 1;
  padding-left: 10px;
}
.headMiddle {
  flex: 1;
  text-align: center;
}

.content {
  height: 100vh;
}
.rightBox {
  display: flex;
  align-items: center;
  flex: 1;
  justify-content: flex-end;
  padding-right: 10px;
}
.icon2 {
  height: 30px;
}
.icon1 {
  width: 30px;
  margin-right: 5px;
}
.bottom {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 54px;
  background: #eee;
  padding: 0 20px;
}
.middle {
  height: calc(100vh - 54px);
  overflow: auto;
}
.itemBox {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}
.item {
  width: 150px;
  margin-bottom: 25px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
  border-radius: 5px;
  overflow: hidden;
}
.itemImgBox {
  width: 150px;
  height: 150px;
  overflow: hidden;
  position: relative;
}
.itemImg {
  width: 100%;
  height: 100%;
}
.itemImgCheckbox {
  position: absolute;
  right: 0;
  top: 5px;
  /* top: 5px;
  width: 10px;
  height: 10px;
  border: 1px solid red;
  z-index: 100; */
}
.itemBottom {
  padding: 5px;
}
.itemName {
  color: #666;
  font-size: 14px;
  width: 150px;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  overflow: hidden;
  line-height: 30px;
}
.itemTime {
  color: #666;
  font-size: 10px;
  width: 150px;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  overflow: hidden;
}
.enter {
  background: rgb(0, 132, 255);
  color: white;
  border: 0;
  padding: 5px 20px;
  border-radius: 5px;
}
.cancel {
  background: rgb(255, 0, 21);
  color: white;
  border: 0;
  padding: 5px 20px;
  border-radius: 5px;
}
</style>
