import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/root/app.component';
import { DashboardComponent } from './components/programmer/dashboard/dashboard.component';
import { ControlPanelComponent } from './components/controlpanel/controlpanel/controlpanel.component';
import { ControlPanelService } from './services/ControlPanelService';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatIconModule } from '@angular/material';

@NgModule({
  declarations: [AppComponent, DashboardComponent, ControlPanelComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule
  ],
  providers: [ControlPanelService],
  bootstrap: [AppComponent]
})
export class AppModule {}
