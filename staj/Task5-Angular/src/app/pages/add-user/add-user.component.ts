import {Component, ElementRef, HostListener, OnDestroy, OnInit, Renderer2} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {Router, RouterModule} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-add-user',
  standalone: true,
    imports: [
        FormsModule,
        RouterModule
    ],
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.scss'
})
export class AddUserComponent implements OnInit {

  userObject: UserObject;

  constructor(private http: HttpClient, private router: Router) {
    this.userObject = new UserObject();
  }

  ngOnInit(): void {}

  onAddUser() {
    this.http.post('http://localhost:8080/auth/register', this.userObject).subscribe((response:any) => {
      if (response.success) {
        this.router.navigate(['/all-users']);
      }
      else {
        alert(response.message);
      }
    });
  }
}

class UserObject {
    name: string;
    email: string;
    password: string;
    roles: string;

    constructor() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.roles = "";
    }
}
