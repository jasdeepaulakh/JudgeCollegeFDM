<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html> 
<html lang="en"> 
<head> 
	<meta charset="utf-8"> 
	<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
          
	<title>Users</title> 
	           
	<link rel="stylesheet" href="https://bootswatch.com/paper/bootstrap.min.css"> 
           
</head>      
<body> 
	<div class="container">
		<div class="page-header" id="banner">
			<div class="bs-component">
           <div class="bs-component">
             <table class="table table-striped table-hover ">
						  <thead>
						    <tr>
						      <th>Username</th>
						      <th>First Name</th>
						      <th>Last Name</th>
						      <th>Email</th>
						      <th>Phone Number</th>
						      <th>Gender</th>
						      <th>D.O.B</th>
						    </tr>
						  </thead>
						  <tbody>
						    <c:forEach items="${usersList}" var="user">
								<tr>
									<td>
										${user.username}
									</td>
									<td>
										${user.firstName}
									</td>
									<td>
										${user.lastName}
									</td>
									<td>
										${user.email}
									</td>
									<td>
										${user.phoneNumber}
									</td>
									<td>
										${user.gender}
									</td>
									<td>
										${user.dob}
									</td>
								</tr>
							</c:forEach>
						  </tbody>
					</table> 
            <div id="source-button" class="btn btn-primary btn-xs" style="display: none;">&lt; &gt;</div></div>
            <div id="source-button" class="btn btn-primary btn-xs" style="display: none;">&lt; &gt;</div></div>
		</div>
	</div>
</body> 
</html> 