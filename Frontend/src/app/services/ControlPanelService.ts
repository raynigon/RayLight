import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ControlPanelService {
  constructor(private http: HttpClient) {}

  public createControlPanel(name: string, data: any): Promise<any> {
    return this.http
      .post('/api/controlpanel', {
        name,
        data: JSON.stringify(data)
      })
      .toPromise();
  }

  public updateControlPanel(controlPanel: any): Promise<any> {
    const data = JSON.stringify(controlPanel.data);
    return this.http.put('/api/controlpanel', {
      id: controlPanel.id,
      name: controlPanel.name,
      data
    }).toPromise();
  }

  public listAllControlPanels(): Promise<any[]> {
    return this.http.get('/api/controlpanel').toPromise() as Promise<any[]>;
  }

  public async getById(id: string): Promise<any> {
    const result = (await this.http
      .get('/api/controlpanel/' + id)
      .toPromise()) as any;
    if (!result.data) {
      result.data = '{}';
    }
    console.log("Parse:", result.data);
    result.data = JSON.parse(result.data);
    return result;
  }
}
