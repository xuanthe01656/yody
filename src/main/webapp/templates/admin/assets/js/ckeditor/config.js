/**
 * @license Copyright (c) 2003-2022, CKSource Holding sp. z o.o. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
   config.filebrowserBrowseUrl = 'ckfinder/ckfinder.html';
   config.filebrowserImageBrowseUrl = 'ckfinder/ckfinder.html?type=Images';
   config.filebrowserFlashBrowseUrl = 'ckfinder/ckfinder.html?type=Flash';
   config.filebrowserUploadUrl = 'ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Files';
   config.filebrowserImageUploadUrl = 'ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Images';
   config.filebrowserFlashUploadUrl = 'ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Flash';
   config.removePlugins = 'easyimage, cloudservices, exportpdf';
	
};
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
