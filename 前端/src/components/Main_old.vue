<template>	<div>		<mt-navbar v-model="selected">		  <mt-tab-item id="1">照片</mt-tab-item>		  <mt-tab-item id="2">相册</mt-tab-item>
		  <mt-tab-item id="3">上传</mt-tab-item>		  <mt-tab-item id="4">我的</mt-tab-item>		</mt-navbar>				<!-- tab-container -->		<mt-tab-container v-model="selected">		  <mt-tab-container-item id="1">		    <Photos></Photos>		  </mt-tab-container-item>		  <mt-tab-container-item id="2">		    <Albums></Albums>		  </mt-tab-container-item>
		  <mt-tab-container-item id="3">
		    <Upload></Upload>
		  </mt-tab-container-item>		  <mt-tab-container-item id="4">		    <My></My>		  </mt-tab-container-item>		</mt-tab-container>	</div></template><script>
	import Photos from "./Photos.vue"
	import Albums from "./Albums.vue"
	import My from "./My.vue"
	import Upload from "./Upload.vue"	export default {	  name: 'Main',	  components: {	  },	  data(){		  return{			selected:'1',
			token:""		  }		  	  },
	  components:{
	  	My:My,
		Albums:Albums,
		Photos:Photos,
		Upload:Upload
	  	
	  
	  }	}</script><style></style>