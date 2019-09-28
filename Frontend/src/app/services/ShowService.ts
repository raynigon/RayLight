import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ShowService {
  constructor(private http: HttpClient) {}

  public playCuelist(cuelist: number): Promise<any> {
    return this.http
      .post(`/api/show/cuelist/${cuelist}/play`, null)
      .toPromise();
  }

  public stopCuelist(cuelist: number): Promise<any> {
    return this.http
      .post(`/api/show/cuelist/${cuelist}/stop`, null)
      .toPromise();
  }

  public goToCue(cuelist: number, cue: number): Promise<any> {
    return this.http
      .post(`/api/show/cuelist/${cuelist}/goto/${cue}`, null)
      .toPromise();
  }
}
