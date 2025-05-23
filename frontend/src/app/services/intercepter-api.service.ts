import { HttpInterceptorFn } from '@angular/common/http';

export const interceptor: HttpInterceptorFn = (req, next) => {

    const token = localStorage.getItem('token');

    // Build headers
    let headers: { [key: string]: string } = {
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
      'Access-Control-Allow-Headers': 'Content-Type, Authorization'
    };

    // Add Authorization header **ONLY IF token exists**
    if (token) {
      headers['Authorization'] = `Bearer ${token}`;
    }
    const modifiedReq = req.clone({ setHeaders: headers });

  return next(modifiedReq);
};
