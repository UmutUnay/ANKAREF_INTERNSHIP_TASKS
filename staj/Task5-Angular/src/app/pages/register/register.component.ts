import {Component, ElementRef, HostListener, OnDestroy, OnInit, Renderer2} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {Router, RouterModule} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-register',
  standalone: true,
    imports: [
        FormsModule,
      RouterModule
    ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent implements OnInit, OnDestroy
{
  isOpened: boolean = false;
  private closeButtonListenerFn: Function | null = null;
  registerObject: Register;

  constructor(private elRef: ElementRef, private renderer: Renderer2, private http: HttpClient, private router: Router)
  {
    this.registerObject = new Register();
  }

  ngOnInit(): void
  {
    this.attachCloseButtonListener();
  }

  ngOnDestroy(): void
  {
    if (this.closeButtonListenerFn)
    {
      this.closeButtonListenerFn();
    }
  }

  openModal(): void
  {
    const modal = document.querySelector('.modal');
    if (modal)
    {
      modal.classList.add('is-open');
      document.body.style.overflow = 'hidden';
    }
  }

  closeModal(): void
  {
    const modal = document.querySelector('.modal');
    if (modal)
    {
      modal.classList.remove('is-open');
      document.body.style.overflow = 'initial';
      this.isOpened = false;
    }
  }

  onRegister(): void
  {
    this.http.post('http://localhost:8080/auth/register', this.registerObject).subscribe((response: any) =>
    {
      if (response.success  )
      {
        this.router.navigate(['/login']);
      }
      else
      {
        alert("Registration Failed");
      }
    });
  }


  // Listeners
  @HostListener('document:click', ['$event'])
  handleOpenRegisterButton(event: MouseEvent): void // Somehow not working
  {
    const modal = document.querySelector('.modal-button');
    if (event.target === modal)
    {
      this.openModal();
    }
  }

  @HostListener('document:click', ['$event'])
  handleRegisterButton(event: MouseEvent): void
  {
    const modal = document.querySelector('.modal-buttons-input-button');
    if (event.target === modal)
    {
      this.onRegister();
    }
  }

  @HostListener('document:keydown.escape', ['$event'])
  handleEscapeKey(event: KeyboardEvent): void
  {
    if (event.key === 'Escape')
    {
      this.closeModal();
    }
  }

  @HostListener('document:keydown.enter', ['$event'])
  handleEnterKey(event: KeyboardEvent): void
  {
    if (event.key === 'Enter')
    {
      this.onRegister();
    }
  }

  @HostListener('window:scroll', ['$event'])
  onWindowScroll(): void {
    if (window.scrollY > window.innerHeight / 3 && !this.isOpened) {
      this.isOpened = true;
      this.openModal();
    }
  }

  attachCloseButtonListener(): void
  {
    const closeButton = this.elRef.nativeElement.querySelector('.close-button');
    if (closeButton)
    {
      this.closeButtonListenerFn = this.renderer.listen(closeButton,'click', () =>
      {
        this.closeModal();
      });
    }
  }

}

class Register
{
  name: string;
  password: string;
  email: string;
  roles: string;

  constructor()
  {
    this.name = '';
    this.email = '';
    this.password = '';
    this.roles = 'ROLE_USER';
  }
}
