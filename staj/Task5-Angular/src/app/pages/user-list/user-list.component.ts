import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-all-events',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.scss'
})
export class UserListComponent implements OnInit {
  users: any[] = [];

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.fetchUsers();
  }

  fetchUsers(): void {
    this.http.get<any[]>('http://localhost:8080/auth/all-users').subscribe(data => {
      this.users = data;
    });
  }

  viewUserDetails(userId: number): void {
    this.router.navigate([`/user-details/${userId}`]);
  }

  addUser(): void {
    this.router.navigate(['/add-user']);
  }

}
