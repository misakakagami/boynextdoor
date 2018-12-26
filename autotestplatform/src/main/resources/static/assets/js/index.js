/**
 * Created by mwq on 2017/12/4.
 */


    function changeFrameHeight(ifm) {

        ifm.height = document.documentElement.clientHeight - 100;
    }
// ========== 选项卡操作 ==========
    $(function() {
        // 选项卡点击
        $(document).on('click', '.content_tab li', function() {
            // 切换选项卡
            $('.content_tab li').removeClass('cur');
            $(this).addClass('cur');
            // 切换iframe
            $('.iframe').removeClass('cur');
            $('#iframe_' + $(this).data('index')).addClass('cur');
            var marginLeft = ($('#tabs').css('marginLeft').replace('px', ''));
            // 滚动到可视区域:在左侧
            if ($(this).position().left < marginLeft) {
                var left = $('.content_tab>ul').scrollLeft() + $(this).position().left - marginLeft;
                $('.content_tab>ul').animate({scrollLeft: left}, 200, function() {
                    initScrollState();
                });
            }
            // 滚动到可视区域:在右侧
            if(($(this).position().left + $(this).width() - marginLeft) > document.getElementById('tabs').clientWidth) {
                var left = $('.content_tab>ul').scrollLeft() + (($(this).position().left + $(this).width() - marginLeft) - document.getElementById('tabs').clientWidth);
                $('.content_tab>ul').animate({scrollLeft: left}, 200, function() {
                    initScrollState();
                });
            }
        });
        // 控制选项卡滚动位置
        $(document).on('click', '.tab_left>a', function() {
            $('.content_tab>ul').animate({scrollLeft: $('.content_tab>ul').scrollLeft() - 300}, 200, function() {
                initScrollState();
            });
        });
        // 向右箭头
        $(document).on('click', '.tab_right>a', function() {
            $('.content_tab>ul').animate({scrollLeft: $('.content_tab>ul').scrollLeft() + 300}, 200, function() {
                initScrollState();
            });
        });
        // 初始化箭头状态

        // 选项卡右键菜单

        var menu = new BootstrapMenu('.tabs li', {
            fetchElementData: function(item) {
                return item;
            },
            actionsGroups: [
                ['close', 'refresh'],
                ['closeOther', 'closeAll'],
                ['closeRight', 'closeLeft']
            ],
            actions: {
                close: {
                    name: '关闭',
                    iconClass: 'zmdi zmdi-close',
                    onClick: function(item) {
                        Tab.closeTab($(item));
                    }
                },
                closeOther: {
                    name: '关闭其他',
                    iconClass: 'zmdi zmdi-arrow-split',
                    onClick: function(item) {
                        var index = $(item).data('index');
                        $('.content_tab li').each(function() {
                            if ($(this).data('index') != index) {
                                Tab.closeTab($(this));
                            }
                        });
                    }
                },
                closeAll: {
                    name: '关闭全部',
                    iconClass: 'zmdi zmdi-swap',
                    onClick: function() {
                        $('.content_tab li').each(function() {
                            Tab.closeTab($(this));
                        });
                    }
                },
                closeRight: {
                    name: '关闭右侧所有',
                    iconClass: 'zmdi zmdi-arrow-right',
                    onClick: function(item) {
                        var index = $(item).data('index');
                        $($('.content_tab li').toArray().reverse()).each(function() {
                            if ($(this).data('index') != index) {
                                Tab.closeTab($(this));
                            } else {
                                return false;
                            }
                        });
                    }
                },
                closeLeft: {
                    name: '关闭左侧所有',
                    iconClass: 'zmdi zmdi-arrow-left',
                    onClick: function(item) {
                        var index = $(item).data('index');
                        $('.content_tab li').each(function() {
                            if ($(this).data('index') != index) {
                                Tab.closeTab($(this));
                            } else {
                                return false;
                            }
                        });
                    }
                },
                refresh: {
                    name: '刷新',
                    iconClass: 'zmdi zmdi-refresh',
                    onClick: function(item) {
                        var index = $(item).data('index');
                        console.log(index)
                        var $iframe = $('#iframe_' + index).find('iframe');
                        $iframe.attr('src', $iframe.attr('src'));
                    }
                }
            }
        });
    });
    /*选项卡对象开始*/

    var Tab = {
        addTab: function(title, url) {
            var index = url.replace(/\./g, '_').replace(/\//g, '_').replace(/:/g, '_').replace(/\?/g, '_').replace(/,/g, '_').replace(/=/g, '_').replace(/&/g, '_');
            // 如果存在选项卡，则激活，否则创建新选项卡
            if ($('#tab_' + index).length == 0) {
                // 添加选项卡
                $('.content_tab li').removeClass('cur');
                var tab = '<li id="tab_' + index +'" data-index="' + index + '" class="cur" style="position: relative">' +
                    '<span class="closeOpen" style="position: absolute;right: 0;top: -10px;font-size: 20px;color:#ccc;z-index: 999">x</span>' +
                    '<a class="waves-effect waves-light">' + title + '</a>' +
                    '</li>';//<i class="zmdi zmdi-close"></i><
                $('.content_tab>ul').append(tab);
                // 添加iframe
                $('.iframe').removeClass('cur');
                var iframe = '<div id="iframe_' + index + '" class="iframe cur"><iframe class="tab_iframe" src="' + url + '" width="100%" frameborder="0" scrolling="auto" onload="changeFrameHeight(this)"></iframe></div>';
                $('.content_main').append(iframe);
                initScrollShow();
                $('.content_tab>ul').animate({scrollLeft: document.getElementById('tabs').scrollWidth - document.getElementById('tabs').clientWidth}, 200, function() {
                    initScrollState();
                });
            } else {
                var $iframe = $('#iframe_' + index).find('iframe');
                $iframe.attr('src', $iframe.attr('src'));
                //2018.1.2 新增 以下一段
                $('#tab_' + index).trigger('click');
            }
            // 点击差号  关闭选项卡
            $("body").on('click','.closeOpen',function () {
                Tab.closeTab($(this).parent());
            })
        },
        closeTab: function($item) {
            var closeable = $item.data('closeable');
            if (closeable != false) {
                // 如果当前时激活状态则关闭后激活左边选项卡
                if($item.hasClass('cur')) {
                    $item.prev().trigger('click');
                }
                // 关闭当前选项卡
                var index = $item.data('index');
                $('#iframe_' + index).remove();
                $item.remove();
            }
            initScrollShow();
        }




    }
    function initScrollShow() {
        if (document.getElementById('tabs').scrollWidth > document.getElementById('tabs').clientWidth) {
            $('.content_tab').addClass('scroll');
        } else {
            $('.content_tab').removeClass('scroll');
        }
    }
    function initScrollState() {
        if ($('.content_tab>ul').scrollLeft() == 0) {
            $('.tab_left>a').removeClass('active');
        } else {
            $('.tab_left>a').addClass('active');
        }
        if (($('.content_tab>ul').scrollLeft() + document.getElementById('tabs').clientWidth) >= document.getElementById('tabs').scrollWidth) {
            $('.tab_right>a').removeClass('active');
        } else {
            $('.tab_right>a').addClass('active');
        }
    }
    $(function () {

        pageInitModule.setsidebar11();


    })
    $(window).resize(function () {
        pageInitModule.setWidth();
    })
    /* 选项卡对象结束*/

    /*
    * init page when page load
    */
    var pageInitModule = (function (mod) {
        mod.setWidth = function () {
            if ($(window).width() < 768) {
                $(".sidebar1").css({ left: -220 });
                $(".all").css({ marginLeft: 0 });
            } else {
                $(".sidebar1").css({ left: 0 });
                $(".all").css({ marginLeft: 220 });
            }
        };
        mod.setsidebar11 = function () {
            $('[data-target="sidebar1"]').click(function () {
                var asideleft = $(".sidebar1").offset().left;
                if (asideleft == 0) {
                    $(".sidebar1").animate({ left: -220 });
                    $(".all").animate({ marginLeft: 0 });
                }
                else {
                    $(".sidebar1").animate({ left: 0 });
                    $(".all").animate({ marginLeft: 220 });
                }
            });
        }
        return mod;
    })(window.pageInitModule || {});
    $(function(){
        $(".nav.nav-list  li").bind({
            mouseover: function() {
                $(this).children("ul").show();
            },
            mouseout: function() {
                $(this).children("ul").hide();
            }
        });
    });






