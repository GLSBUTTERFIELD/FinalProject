import { CommonModule } from '@angular/common';
import { Component, AfterViewInit } from '@angular/core';
declare var $: any;
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-nav-bar',
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})

export class NavBarComponent {

  showSignUpForm: boolean = false;

  toggleSignUp() {
    this.showSignUpForm = !this.showSignUpForm;
  }

  ngAfterViewInit() {
    $('#signUpModal').modal({ show: false });
  }

  openModal() {
    $('#signUpModal').modal('show');
  }

  closeModal() {
    $('#signUpModal').modal('hide');
  }
}
