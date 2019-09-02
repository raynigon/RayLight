import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/root/app.component';
import { DashboardComponent } from './components/programmer/dashboard/dashboard.component';
import { ControlPanelComponent } from './components/controlpanel/controlpanel/controlpanel.component';
import { ControlPanelService } from './services/ControlPanelService';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatIconModule,
  MatMenuModule,
  MatDialogModule,
  MatExpansionModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule
} from '@angular/material';
import { DMXService } from './services/DMXService';
import { ButtonPanelComponent } from './components/controlpanel/buttonpanel/buttonpanel.component';
import { SliderPanelComponent } from './components/controlpanel/sliderpanel/sliderpanel.component';
import { ButtonEditDialogComponent } from './components/controlpanel/editdialogs/buttoneditdialog/buttoneditdialog.component';
import { FormsModule } from '@angular/forms';
import { SliderEditDialogComponent } from './components/controlpanel/editdialogs/slidereditdialog/slidereditdialog.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ControlPanelComponent,
    ButtonPanelComponent,
    SliderPanelComponent,
    ButtonEditDialogComponent,
    SliderEditDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatDialogModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
  ],
  entryComponents: [
    ButtonEditDialogComponent,
    SliderEditDialogComponent,
  ],
  providers: [ControlPanelService, DMXService],
  bootstrap: [AppComponent]
})
export class AppModule {}
