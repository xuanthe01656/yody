/*(function (jQuery) {
    "use strict";
    // data-mode="click" for using event
    // data-dark="false" for property
    // icon class // la-sun // la-moon
    const storageDark = localStorage.getItem('dark')
    console.log(storageDark)
    if($('body').hasClass('dark')){
        changeMode('true');
    } else {
        changeMode('true');
    }
    if (storageDark !== 'null') {
        changeMode(storageDark)
    }
    jQuery(document).on("change", '.change-mode input[type="checkbox"]' ,function (e) {
        const dark = $(this).attr('data-active');
        if (dark === 'true') {
            $(this).attr('data-active','false')
        } else {
            $(this).attr('data-active','true')
        }
        changeMode(dark)
    })
    function changeMode (dark) {
        const body = jQuery('body')
        if (dark === 'true') {
            $('[data-mode="toggle"]').find('i.a-right').removeClass('ri-sun-line');
            $('[data-mode="toggle"]').find('i.a-left').addClass('ri-moon-clear-line');
            $('#dark-mode').prop('checked', true).attr('data-active', 'false')
            $('.darkmode-logo').removeClass('d-none')
            $('.light-logo').addClass('d-none')
            body.addClass('dark')
            dark = true
        } else {
            $('[data-mode="toggle"]').find('i.a-left').removeClass('ri-moon-clear-line');
            $('[data-mode="toggle"]').find('i.a-right').addClass('ri-sun-line');
            $('#dark-mode').prop('checked', false).attr('data-active', 'true');
            $('.light-logo').removeClass('d-none')
            $('.darkmode-logo').addClass('d-none')
            body.removeClass('dark')
            dark = false
        }
        updateLocalStorage(dark)
        const event = new CustomEvent("ChangeColorMode", {detail: {dark: dark} });
        document.dispatchEvent(event);
    }
    function updateLocalStorage(dark) {
        localStorage.setItem('dark', dark)
    }
    
})(jQuery)*/
$(document).ready(function(){
     $(".form").validate({
			ignore:[],
			rules:{
				newcat:{
					required: true,
					minlength: 2,
					maxlength: 50,
				},
				username: {
					required: true,
					minlength: 6,
					maxlength: 32,
				},
				password: {
					required:  true,
					minlength: 6,
				},
				repassword:{
					equalTo: "#password"
				},
				fullname:{
					required: true,
				},
				address:{
					required: true,
				},
				birthday:{
					required: true,
					dateISO: true,
				},
				phone:{
					required: true,
					digits: true,
					maxlength: 11,
					
				},
				email: {
					
					required: true,
					email: true,
					minlength: 11,
					maxlength: 150,
				},
				facebook:{
					url: true,
				},
				moreinfo:{
					required: true,
				},
				
			},
			messages:{
				newcat: {
					required: " Hãy nhập vào category.",
					minlength: " Hãy nhập vào nhiều hơn 2 ký tự và ít hơn 50 ký tự.",
					maxlength: " Hãy nhập vào ít hơn 50 ký tự.",
				},
				username: {
					required: " Hãy nhập vào username.",
					minlength: " Hãy nhập vào nhiều hơn 6 ký tự và ít hơn 32 ký tự.",
					maxlength: " Hãy nhập vào ít hơn 32 ký tự.",
				},
				password: {
					required: " Hãy nhập vào password.",
					minlength: " Hãy nhập vào nhiều hơn 6 ký tự.",
				},
				repassword:{
					equalTo: "Vui lòng nhập trùng với mật khẩu."
				},
				fullname:{
					required: "Hãy nhập vào tên đầy đủ",
				},
				address:{
					required: "Hãy nhập vào địa chỉ.",
				},
				birthday:{
					required: "Hãy nhập vào ngày tháng năm sinh",
					dateISO: "Hãy nhập vào đúng định dạng yyyy/mm/dd.",
				},
				phone:{
					required: "Hãy nhập vào số điện thoại",
					digits: " Hãy nhập là số nguyên",
					maxlength: "Tối đa 11 số.",
				},
				email: {
					required: "Hãy nhập vào email",
					email: "Hãy nhập đúng định dạng",
					minlength: " Hãy nhập vào nhiều hơn 11 ký tự và ít hơn 150 ký tự.",
					maxlength: " Hãy nhập vào ít hơn 150 ký tự.",
				},
				facebook:{
					url: "Hãy nhập đúng định dạng",
				},
				moreinfo:{
					required: "Hãy nhập giới thiệu",
				},
			},
		});
		/*$('.form').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            newcat: {
                validators: {
                    stringLength: {
						min: 2,
                        max: 50,
                        message: 'The name must be more than 2 characters',
                        message: 'The name must be less than 50 characters'
                    }
                }
            },
            bio: {
                validators: {
                    stringLength: {
                        max: 200,
                        message: 'The bio must be less than 200 characters'
                    }
                }
            }
        }
    });*/
     });
CKEDITOR.on('instanceReady', function () {
    $.each(CKEDITOR.instances, function (instance) {
        CKEDITOR.instances[instance].document.on("keyup", CK_jQ);
        CKEDITOR.instances[instance].document.on("paste", CK_jQ);
        CKEDITOR.instances[instance].document.on("keypress", CK_jQ);
        CKEDITOR.instances[instance].document.on("blur", CK_jQ);
        CKEDITOR.instances[instance].document.on("change", CK_jQ);
    });
});

function CK_jQ() {
    for (instance in CKEDITOR.instances) {
        CKEDITOR.instances[instance].updateElement();
    }
}
CKEDITOR_CONFIGS = {
    'default': {
        'skin': 'moono',
        'toolbar': 'basic',
        'height': 'full',
        'width': 'full',
        'removePlugins': 'exportpdf',
        'toolbarCanCollapse':'true'
    },
}