import {Component, ElementRef, HostListener, OnDestroy, OnInit, Renderer2} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {Router, RouterModule} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    RouterModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit, OnDestroy {
  isOpened: boolean = false;
  private closeButtonListenerFn: Function | null = null;
  loginObject: Login;

  constructor(private elRef: ElementRef, private renderer: Renderer2, private http: HttpClient, private router: Router) {
    this.loginObject = new Login();
  }

  ngOnInit(): void {
    this.attachCloseButtonListener();
  }


  openModal(): void {
    const modal = document.querySelector('.modal');
    if (modal) {
      modal.classList.add('is-open');
      document.body.style.overflow = 'hidden';
    }
  }

  closeModal(): void {
    const modal = document.querySelector('.modal');
    if (modal) {
      modal.classList.remove('is-open');
      document.body.style.overflow = 'initial';
      this.isOpened = false;
    }
  }

  ngOnDestroy(): void {
    if (this.closeButtonListenerFn) {
      this.closeButtonListenerFn();
    }
  }

  onLogin(): void {
    this.http.post<string>('http://localhost:8080/auth/generateToken', this.loginObject).subscribe((response:any) => {
      alert(`Token: ${response.token}`)
      if(response.result)
      {
        alert("Login Success")
        localStorage.setItem('token', response.token);
        this.router.navigate(['/dashboard']);
      }
      else
      {
        alert("Login Failed")
      }
    })
  }

  // Listeners
  @HostListener('window:scroll', ['$event'])
  onWindowScroll(): void {
    if (window.scrollY > window.innerHeight / 3 && !this.isOpened) {
      this.isOpened = true;
      this.openModal();
    }
  }

  @HostListener('document:keydown.escape', ['$event'])
  handleKeyboardEventEscape (event: KeyboardEvent): void {
    if (event.key === 'Escape') {
      this.closeModal();
    }
  }

  @HostListener('document:keydown.enter', ['$event'])
  handleKeyboardEventEnter(event: KeyboardEvent): void {
    if (event.key === 'Enter') {
      this.onLogin();
    }
  }

  attachCloseButtonListener(): void {
    const closeButton = this.elRef.nativeElement.querySelector('.close-button');
    if (closeButton) {
      this.closeButtonListenerFn = this.renderer.listen(closeButton, 'click', () => {
        this.closeModal();
      });
    }
  }

  @HostListener('document:click', ['$event'])
  handleOpenLogInButton(event: MouseEvent): void {
    const modal = document.querySelector('.modal-button');
    if (event.target === modal) {
      this.openModal();
    }
  }

  @HostListener('document:click', ['$event'])
  handleLogInButton(event: MouseEvent): void {
    const modal = document.querySelector('.modal-buttons-input-button');
    if (event.target === modal) {
      this.onLogin();
    }
  }

  @HostListener('document:click', ['$event'])
  handleRegisterButton(event: MouseEvent): void {
    const modal = document.querySelector('.modal-buttons-a');
    if (event.target === modal) {
      this.router.navigate(['/register']);
    }
  }

}

export class Login
{
  username: string;
  password: string;
  constructor()
  {
    this.username = "";
    this.password = "";
  }
}
