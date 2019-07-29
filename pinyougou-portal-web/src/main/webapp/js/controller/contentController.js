﻿var app = new Vue({
    el: "#app",
    data: {
        pages:15,
        pageNo:1,
        list:[],
        entity:{},
        contentList:[],
        ids:[],
        keywords:'',  //获取关键字
        searchEntity:{}
    },
    methods: {
        searchList:function (curPage) {
            axios.post('/content/search?pageNo='+curPage,this.searchEntity).then(function (response) {
                //获取数据
                app.list=response.data.list;

                //当前页
                app.pageNo=curPage;
                //总页数
                app.pages=response.data.pages;
            });
        },
        //查询所有品牌列表
        findAll:function () {
            console.log(app);
            axios.get('/content/findAll').then(function (response) {
                console.log(response);
                //注意：this 在axios中就不再是 vue实例了。
                app.list=response.data;

            }).catch(function (error) {

            })
        },
         findPage:function () {
            var that = this;
            axios.get('/content/findPage',{params:{
                pageNo:this.pageNo
            }}).then(function (response) {
                console.log(app);
                //注意：this 在axios中就不再是 vue实例了。
                app.list=response.data.list;
                app.pageNo=curPage;
                //总页数
                app.pages=response.data.pages;
            }).catch(function (error) {

            })
        },
        //该方法只要不在生命周期的
        add:function () {
            axios.post('/content/add',this.entity).then(function (response) {
                console.log(response);
                if(response.data.success){
                    app.searchList(1);
                }
            }).catch(function (error) {
                console.log("1231312131321");
            });
        },
        update:function () {
            axios.post('/content/update',this.entity).then(function (response) {
                console.log(response);
                if(response.data.success){
                    app.searchList(1);
                }
            }).catch(function (error) {
                console.log("1231312131321");
            });
        },
        save:function () {
            if(this.entity.id!=null){
                this.update();
            }else{
                this.add();
            }
        },
        findOne:function (id) {
            axios.get('/content/findOne/'+id).then(function (response) {
                app.entity=response.data;
            }).catch(function (error) {
                console.log("1231312131321");
            });
        },
        dele:function () {
            axios.post('/content/delete',this.ids).then(function (response) {
                console.log(response);
                if(response.data.success){
                    app.searchList(1);
                }
            }).catch(function (error) {
                console.log("1231312131321");
            });
        },

        //根据广告类型id查询列表
        findByCategoryId:function (categoryId) {
            axios.get('/content/findByCategoryId/'+categoryId).then(function(response) {
                app.contentList = response.data;
            }).catch(function (error) {
                console.log("123456");
            })
        },

        //添加搜索方法
        doSearch:function () {
            window.location.href="http://localhost:9104/search.html?keywords="+encodeURIComponent(this.keywords);
        },
        goodsItem:function (parentId) {
            axios.post('/itemCat/goodsItem/'+parentId).then(
                function (response) {
                    app.list=response.data
                }
            )
        }



    },
    //钩子函数 初始化了事件和
    created: function () {
        this.findByCategoryId(1);
        this.goodsItem(0)
    }

})
