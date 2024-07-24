import { HttpInterceptorFn } from '@angular/common/http';

export const customInterceptor: HttpInterceptorFn = (req, next) => {

  const loginUrl = 'http://localhost:8080/auth/generateToken';
  if (req.url === loginUrl) {
    const token = localStorage.getItem('token');
    const clone = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(clone);
  }
  else
  {
    return next(req);
  }
};
