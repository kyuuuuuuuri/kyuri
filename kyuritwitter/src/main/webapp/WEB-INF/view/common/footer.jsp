<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>

<c:if test="${hasPrev}">
	<a href="?page=${page-1 }">&lt;次へ</a>
</c:if>
<c:if test="${hasNext}">
	<a href="?page=${page+1 }">前へ&gt;</a>
</c:if>