package controller.ad;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import library.AuthUtil;
import library.UploadFileLB;
import model.bean.Admin;
import model.bean.colur;
import model.bean.productDetail;
import model.dao.ColourDAO;
import model.dao.ProductDao;
import model.dao.SizeDAO;
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
		maxFileSize = 1024 * 1024 * 50, // 50 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
	)
public class addProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "templates/admin/assets/images/table/product";
	
    public addProductController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/backend/page-add-product.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		HttpSession session = request.getSession();
		ProductDao objPrDAO = new ProductDao();
		ColourDAO objClDAO = new ColourDAO();
		SizeDAO objSDAO = new SizeDAO();
		Admin objU = (Admin) session.getAttribute("objUAd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date dateNow = new Date();
		List<String> photo = UploadFileLB.uploadFileDisplay(UPLOAD_DIR, request);
		List<String> photos = UploadFileLB.uploadFile(UPLOAD_DIR, request);
			
			if("".equals(request.getParameter("color"))==false&&"".equals(request.getParameter("size"))==false&&"".equals(request.getParameter("color-text"))&&"".equals(request.getParameter("size-text"))) {
				int category_id= Integer.parseInt(request.getParameter("category"));
				String product_code = request.getParameter("suk");
				String name = request.getParameter("name");
				int color_id = Integer.parseInt(request.getParameter("color"));
				int size_id = Integer.parseInt(request.getParameter("size"));
				int menu_id = Integer.parseInt(request.getParameter("menu"));
				int menu_detail_id = Integer.parseInt(request.getParameter("categorymenu"));
				double cost=0.0;
				double price=0.0;
				String outstanding_feature="";
				String detail="";
				if(request.getParameter("cost")!=null&&request.getParameter("price")!=null&&request.getParameter("outstandingfeature")!=null&&request.getParameter("detail")!=null) {
					cost = Double.parseDouble(request.getParameter("cost"));
					price = Double.parseDouble(request.getParameter("price"));
					outstanding_feature = request.getParameter("outstandingfeature");
					detail = request.getParameter("detail");
				}
				int suppliers_id = Integer.parseInt(request.getParameter("suppliers"));
				int amount = Integer.parseInt(request.getParameter("amount"));
				String image = StringUtils.join(photo, ",");
				String list_images = StringUtils.join(photos, ",");
				
				int country_id = Integer.parseInt(request.getParameter("country"));
				int created_at = objU.getId();
				String created_date = sdf.format(dateNow);
				
				
				ArrayList<productDetail> listPro = objPrDAO.getItemBySUK(product_code);
				if(listPro.size()>0) {
					productDetail newPro = new productDetail();
					for(int i=0; i<listPro.size(); i++ ) {
						if(listPro.get(i).getProductSKU().equals(product_code)&&listPro.get(i).getName().equalsIgnoreCase(name)&&listPro.get(i).getColor_id()==color_id&&listPro.get(i).getSize_id()==size_id) {
							newPro = listPro.get(i);
						}else {
							if(listPro.get(i).getProductSKU().equals(product_code)&&listPro.get(i).getName().equalsIgnoreCase(name)&&listPro.get(i).getColor_id()!=color_id&&listPro.get(i).getSize_id()==size_id) {
								newPro = listPro.get(i);
							}else {
								if(listPro.get(i).getProductSKU().equals(product_code)&&listPro.get(i).getName().equalsIgnoreCase(name)&&listPro.get(i).getColor_id()==color_id&&listPro.get(i).getSize_id()!=size_id) {
									newPro = listPro.get(i);
								}
							}
						}
					}
					if(newPro.getName()!=null) {
						if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()==color_id&&newPro.getSize_id()==size_id) {
							int update_at = objU.getId();
							int result = objPrDAO.UpdateAmount(amount, newPro, update_at);
							if(result>0) {
								response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=3");
							}else {
								response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=4");
							}	
						}else {
							if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()!=color_id&&newPro.getSize_id()==size_id) {
								int update_at = objU.getId();
								int result = objPrDAO.addNewProByColor(newPro,color_id,image,list_images,amount, update_at, created_date);
								if(result>0) {
									response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
								}else {
									response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
								}	
							}else {
								if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()==color_id&&newPro.getSize_id()!=size_id) {
									int update_at = objU.getId();
									int result = objPrDAO.addNewProBySize(newPro,size_id,amount, update_at, created_date);
									if(result>0) {
										response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
									}else {
										response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
									}	
								}else {
									if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()!=color_id&&newPro.getSize_id()!=size_id) {
										int update_at = objU.getId();
										int result = objPrDAO.addNewProByColorSize(newPro,color_id,size_id,image,list_images,amount, update_at, created_date);
										if(result>0) {
											response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
										}else {
											response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
										}	
									}
								}
							}
						}
						
					}else {
						productDetail objPD = new productDetail(0, product_code, name, color_id, size_id, amount, cost, price, outstanding_feature, detail, 0, image, list_images, 0, menu_detail_id, category_id, menu_id, suppliers_id, country_id, created_at, 0, created_date, 0);
						int result = objPrDAO.addProduct(objPD);
						if(result>0) {
							response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
						}else {
							response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
						}		
					}
				}else {
					productDetail objPD = new productDetail(0, product_code, name, color_id, size_id, amount, cost, price, outstanding_feature, detail, 0, image, list_images, 0, menu_detail_id, category_id, menu_id, suppliers_id, country_id, created_at, 0, created_date, 0);
					int result = objPrDAO.addProduct(objPD);
					if(result>0) {
						response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
					}else {
						response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
					}		
				}
			}else {
				if("".equals(request.getParameter("color"))&&"".equals(request.getParameter("size"))&&"".equals(request.getParameter("color-text"))==false&&"".equals(request.getParameter("size-text"))==false) {
					int category_id= Integer.parseInt(request.getParameter("category"));
					String size = request.getParameter("size-text");
					String color = request.getParameter("color-text");
					
					String created_date = sdf.format(dateNow);
					int resultColor = objClDAO.addColor(color, objU.getId(), created_date);
					int resultSize = objSDAO.addSize(size, objU.getId(), created_date);
					if(resultColor>0&&resultSize>0) {
						colur objCl = objClDAO.getColorByName(color);
						model.bean.size objSize = objSDAO.getSizeByName(size);
						String product_code = request.getParameter("suk");
						String name = request.getParameter("name");
						int color_id = objCl.getId();
						int size_id = objSize.getId();
						int menu_id = Integer.parseInt(request.getParameter("menu"));
						int menu_detail_id = Integer.parseInt(request.getParameter("categorymenu"));
						double cost=0.0;
						double price=0.0;
						String outstanding_feature="";
						String detail="";
						if(request.getParameter("cost")!=null&&request.getParameter("price")!=null&&request.getParameter("outstandingfeature")!=null&&request.getParameter("detail")!=null) {
							cost = Double.parseDouble(request.getParameter("cost"));
							price = Double.parseDouble(request.getParameter("price"));
							outstanding_feature = request.getParameter("outstandingfeature");
							detail = request.getParameter("detail");
						}
						int suppliers_id = Integer.parseInt(request.getParameter("suppliers"));
						int amount = Integer.parseInt(request.getParameter("amount"));
						String image = StringUtils.join(photo, ",");
						String list_images = StringUtils.join(photos, ",");
						int country_id = Integer.parseInt(request.getParameter("country"));
						int created_at = objU.getId();
						ArrayList<productDetail> listPro = objPrDAO.getItemBySUK(product_code);
						if(listPro.size()>0) {
							productDetail newPro = new productDetail();
							for(int i=0; i<listPro.size(); i++ ) {
								if(listPro.get(i).getProductSKU().equals(product_code)&&listPro.get(i).getName().equalsIgnoreCase(name)&&listPro.get(i).getColor_id()==color_id&&listPro.get(i).getSize_id()==size_id) {
									newPro = listPro.get(i);
								}else {
									if(listPro.get(i).getProductSKU().equals(product_code)&&listPro.get(i).getName().equalsIgnoreCase(name)&&listPro.get(i).getColor_id()!=color_id&&listPro.get(i).getSize_id()==size_id) {
										newPro = listPro.get(i);
									}else {
										if(listPro.get(i).getProductSKU().equals(product_code)&&listPro.get(i).getName().equalsIgnoreCase(name)&&listPro.get(i).getColor_id()==color_id&&listPro.get(i).getSize_id()!=size_id) {
											newPro = listPro.get(i);
										}
									}
								}
							}
							
							if(newPro.getName()!=null) {
								if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()==color_id&&newPro.getSize_id()==size_id) {
									int update_at = objU.getId();
									int result = objPrDAO.UpdateAmount(amount, newPro, update_at);
									if(result>0) {
										response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=3");
									}else {
										response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=4");
									}	
								}else {
									if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()!=color_id&&newPro.getSize_id()==size_id) {
										int update_at = objU.getId();
										int result = objPrDAO.addNewProByColor(newPro,color_id,image,list_images,amount, update_at, created_date);
										if(result>0) {
											response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
										}else {
											response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
										}	
									}else {
										if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()==color_id&&newPro.getSize_id()!=size_id) {
											int update_at = objU.getId();
											int result = objPrDAO.addNewProBySize(newPro,size_id,amount, update_at, created_date);
											if(result>0) {
												response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
											}else {
												response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
											}	
										}else {
											if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()!=color_id&&newPro.getSize_id()!=size_id) {
												int update_at = objU.getId();
												int result = objPrDAO.addNewProByColorSize(newPro,color_id,size_id,image,list_images,amount, update_at, created_date);
												if(result>0) {
													response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
												}else {
													response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
												}	
											}
										}
									}
								}
								
							}else {
								productDetail objPD = new productDetail(0, product_code, name, color_id, size_id, amount, cost, price, outstanding_feature, detail, 0, image, list_images, 0, menu_detail_id, category_id, menu_id, suppliers_id, country_id, created_at, 0, created_date, 0);
								int result = objPrDAO.addProduct(objPD);
								if(result>0) {
									response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
								}else {
									response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
								}		
							}
						}else {
							productDetail objPD = new productDetail(0, product_code, name, color_id, size_id, amount, cost, price, outstanding_feature, detail, 0, image, list_images, 0, menu_detail_id, category_id, menu_id, suppliers_id, country_id, created_at, 0, created_date, 0);
							int result = objPrDAO.addProduct(objPD);
							if(result>0) {
								response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
							}else {
								response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
							}		
						}
					}
					
				}else {
					if("".equals(request.getParameter("color"))==false&&"".equals(request.getParameter("size"))&&"".equals(request.getParameter("color-text"))&&"".equals(request.getParameter("size-text"))==false) {
						int category_id= Integer.parseInt(request.getParameter("category"));
						String size = request.getParameter("size-text");
						String created_date = sdf.format(dateNow);
						int resultSize = objSDAO.addSize(size, objU.getId(), created_date);
						if(resultSize>0) {
							model.bean.size objSize = objSDAO.getSizeByName(size);
							String product_code = request.getParameter("suk");
							String name = request.getParameter("name");
							int color_id = Integer.parseInt(request.getParameter("color"));
							int size_id = objSize.getId();
							int menu_id = Integer.parseInt(request.getParameter("menu"));
							int menu_detail_id = Integer.parseInt(request.getParameter("categorymenu"));
							double cost=0.0;
							double price=0.0;
							String outstanding_feature="";
							String detail="";
							if(request.getParameter("cost")!=null&&request.getParameter("price")!=null&&request.getParameter("outstandingfeature")!=null&&request.getParameter("detail")!=null) {
								cost = Double.parseDouble(request.getParameter("cost"));
								price = Double.parseDouble(request.getParameter("price"));
								outstanding_feature = request.getParameter("outstandingfeature");
								detail = request.getParameter("detail");
							}
							int suppliers_id = Integer.parseInt(request.getParameter("suppliers"));
							int amount = Integer.parseInt(request.getParameter("amount"));
							String image = StringUtils.join(photo, ",");
							String list_images = StringUtils.join(photos, ",");
							int country_id = Integer.parseInt(request.getParameter("country"));
							int created_at = objU.getId();
							ArrayList<productDetail> listPro = objPrDAO.getItemBySUK(product_code);
							if(listPro.size()>0) {
								productDetail newPro = new productDetail();
								for(int i=0; i<listPro.size(); i++ ) {
									if(listPro.get(i).getProductSKU().equals(product_code)&&listPro.get(i).getName().equalsIgnoreCase(name)&&listPro.get(i).getColor_id()==color_id&&listPro.get(i).getSize_id()==size_id) {
										newPro = listPro.get(i);
									}else {
										if(listPro.get(i).getProductSKU().equals(product_code)&&listPro.get(i).getName().equalsIgnoreCase(name)&&listPro.get(i).getColor_id()!=color_id&&listPro.get(i).getSize_id()==size_id) {
											newPro = listPro.get(i);
										}else {
											if(listPro.get(i).getProductSKU().equals(product_code)&&listPro.get(i).getName().equalsIgnoreCase(name)&&listPro.get(i).getColor_id()==color_id&&listPro.get(i).getSize_id()!=size_id) {
												newPro = listPro.get(i);
											}
										}
									}
								}
								
								if(newPro.getName()!=null) {
									if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()==color_id&&newPro.getSize_id()==size_id) {
										int update_at = objU.getId();
										int result = objPrDAO.UpdateAmount(amount, newPro, update_at);
										if(result>0) {
											response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=3");
										}else {
											response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=4");
										}	
									}else {
										if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()!=color_id&&newPro.getSize_id()==size_id) {
											int update_at = objU.getId();
											int result = objPrDAO.addNewProByColor(newPro,color_id, image,list_images,amount, update_at, created_date);
											if(result>0) {
												response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
											}else {
												response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
											}	
										}else {
											if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()==color_id&&newPro.getSize_id()!=size_id) {
												int update_at = objU.getId();
												int result = objPrDAO.addNewProBySize(newPro,size_id,amount, update_at, created_date);
												if(result>0) {
													response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
												}else {
													response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
												}	
											}else {
												if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()!=color_id&&newPro.getSize_id()!=size_id) {
													int update_at = objU.getId();
													int result = objPrDAO.addNewProByColorSize(newPro,color_id,size_id,image,list_images,amount, update_at, created_date);
													if(result>0) {
														response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
													}else {
														response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
													}	
												}
											}
										}
									}
									
								}else {
									productDetail objPD = new productDetail(0, product_code, name, color_id, size_id, amount, cost, price, outstanding_feature, detail, 0, image, list_images, 0, menu_detail_id, category_id, menu_id, suppliers_id, country_id, created_at, 0, created_date, 0);
									int result = objPrDAO.addProduct(objPD);
									if(result>0) {
										response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
									}else {
										response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
									}		
								}
							}else {
								productDetail objPD = new productDetail(0, product_code, name, color_id, size_id, amount, cost, price, outstanding_feature, detail, 0, image, list_images, 0, menu_detail_id, category_id, menu_id, suppliers_id, country_id, created_at, 0, created_date, 0);
								int result = objPrDAO.addProduct(objPD);
								if(result>0) {
									response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
								}else {
									response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
								}		
							}
						}
					}else {
						if("".equals(request.getParameter("color"))&&"".equals(request.getParameter("size"))==false&&"".equals(request.getParameter("color-text"))==false&&"".equals(request.getParameter("size-text"))) {
							int category_id= Integer.parseInt(request.getParameter("category"));
							String color = request.getParameter("color-text");
							String created_date = sdf.format(dateNow);
							int resultColor = objClDAO.addColor(color, objU.getId(), created_date);
							if(resultColor>0) {
								colur objCl = objClDAO.getColorByName(color);
								String product_code = request.getParameter("suk");
								String name = request.getParameter("name");
								int color_id = objCl.getId();
								int size_id = Integer.parseInt(request.getParameter("size"));
								int menu_id = Integer.parseInt(request.getParameter("menu"));
								int menu_detail_id = Integer.parseInt(request.getParameter("categorymenu"));
								double cost=0.0;
								double price=0.0;
								String outstanding_feature="";
								String detail="";
								if(request.getParameter("cost")!=null&&request.getParameter("price")!=null&&request.getParameter("outstandingfeature")!=null&&request.getParameter("detail")!=null) {
									cost = Double.parseDouble(request.getParameter("cost"));
									price = Double.parseDouble(request.getParameter("price"));
									outstanding_feature = request.getParameter("outstandingfeature");
									detail = request.getParameter("detail");
								}
								int suppliers_id = Integer.parseInt(request.getParameter("suppliers"));
								int amount = Integer.parseInt(request.getParameter("amount"));
								String image = StringUtils.join(photo, ",");
								String list_images = StringUtils.join(photos, ",");
								int country_id = Integer.parseInt(request.getParameter("country"));
								int created_at = objU.getId();
								ArrayList<productDetail> listPro = objPrDAO.getItemBySUK(product_code);
								if(listPro.size()>0) {
									productDetail newPro = new productDetail();
									for(int i=0; i<listPro.size(); i++ ) {
										if(listPro.get(i).getProductSKU().equals(product_code)&&listPro.get(i).getName().equalsIgnoreCase(name)&&listPro.get(i).getColor_id()==color_id&&listPro.get(i).getSize_id()==size_id) {
											newPro = listPro.get(i);
										}else {
											if(listPro.get(i).getProductSKU().equals(product_code)&&listPro.get(i).getName().equalsIgnoreCase(name)&&listPro.get(i).getColor_id()!=color_id&&listPro.get(i).getSize_id()==size_id) {
												newPro = listPro.get(i);
											}else {
												if(listPro.get(i).getProductSKU().equals(product_code)&&listPro.get(i).getName().equalsIgnoreCase(name)&&listPro.get(i).getColor_id()==color_id&&listPro.get(i).getSize_id()!=size_id) {
													newPro = listPro.get(i);
												}
											}
										}
									}
									
									if(newPro.getName()!=null) {
										if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()==color_id&&newPro.getSize_id()==size_id) {
											int update_at = objU.getId();
											int result = objPrDAO.UpdateAmount(amount, newPro, update_at);
											if(result>0) {
												response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=3");
											}else {
												response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=4");
											}	
										}else {
											if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()!=color_id&&newPro.getSize_id()==size_id) {
												int update_at = objU.getId();
												int result = objPrDAO.addNewProByColor(newPro,color_id,image,list_images,amount, update_at, created_date);
												if(result>0) {
													response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
												}else {
													response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
												}	
											}else {
												if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()==color_id&&newPro.getSize_id()!=size_id) {
													int update_at = objU.getId();
													int result = objPrDAO.addNewProBySize(newPro,size_id,amount, update_at, created_date);
													if(result>0) {
														response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
													}else {
														response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
													}	
												}else {
													if(newPro.getProductSKU().equals(product_code)&&newPro.getName().equalsIgnoreCase(name)&&newPro.getColor_id()!=color_id&&newPro.getSize_id()!=size_id) {
														int update_at = objU.getId();
														int result = objPrDAO.addNewProByColorSize(newPro,color_id,size_id,image, list_images,amount, update_at, created_date);
														if(result>0) {
															response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
														}else {
															response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
														}	
													}
												}
											}
										}
										
									}else {
										productDetail objPD = new productDetail(0, product_code, name, color_id, size_id, amount, cost, price, outstanding_feature, detail, 0, image, list_images, 0, menu_detail_id, category_id, menu_id, suppliers_id, country_id, created_at, 0, created_date, 0);
										int result = objPrDAO.addProduct(objPD);
										if(result>0) {
											response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
										}else {
											response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
										}		
									}
								}else {
									productDetail objPD = new productDetail(0, product_code, name, color_id, size_id, amount, cost, price, outstanding_feature, detail, 0, image, list_images, 0, menu_detail_id, category_id, menu_id, suppliers_id, country_id, created_at, 0, created_date, 0);
									int result = objPrDAO.addProduct(objPD);
									if(result>0) {
										response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=1");
									}else {
										response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=2");
									}		
								}
							}
						}else {
							if("".equals(request.getParameter("color"))==false&&"".equals(request.getParameter("size"))==false&&"".equals(request.getParameter("color-text"))==false&&"".equals(request.getParameter("size-text"))==false){
								response.sendRedirect(request.getContextPath()+"/admin/add-product?msg=5");
							}
						}
					}
				}
			}
		}
}
