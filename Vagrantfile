Vagrant.configure('2') do |config|

  config.vm.box = 'chef/centos-7.0'
  config.ssh.forward_agent = true
  config.cache.enable :yum if Vagrant.has_plugin?("vagrant-cachier")

  config.vm.provider 'virtualbox' do |vb|
    vb.customize ['modifyvm', :id, '--memory', '512']
  end

  config.vm.define 'bastion', primary: true do |lb|
    lb.vm.provision :shell, inline: '/bin/bash /vagrant/scripts/vagrant/bastion-node-install'
    lb.vm.network 'private_network', ip: '10.16.0.180'
  end

  config.vm.define 'lb' do |lb|
    lb.vm.provision :shell, inline: '/bin/bash /vagrant/scripts/prod-like/lb-node-install 10.16.0.13 10.16.0.12'
    lb.vm.provision :shell, inline: '/bin/bash /vagrant/scripts/vagrant/lb-node-install lb'
    lb.vm.network 'forwarded_port', guest: 80, host: 8081
    lb.vm.network 'private_network', ip: '10.16.0.10'
  end

  config.vm.define 'app1' do |app|
    app.vm.provision :shell, inline: '/bin/bash /vagrant/scripts/prod-like/app-node-install'
    app.vm.provision :shell, inline: '/bin/bash /vagrant/scripts/vagrant/app-node-install app1'
    app.vm.network 'forwarded_port', guest: 8080, host: 8082
    app.vm.network 'private_network', ip: '10.16.0.13'
  end

  config.vm.define 'app2' do |app|
    app.vm.provision :shell, inline: '/bin/bash /vagrant/scripts/prod-like/app-node-install'
    app.vm.provision :shell, inline: '/bin/bash /vagrant/scripts/vagrant/app-node-install app2'
    app.vm.network 'forwarded_port', guest: 8080, host: 8083
    app.vm.network 'private_network', ip: '10.16.0.12'
  end

  config.vm.define 'es1' do |es|
    es.vm.provision :shell, inline: '/bin/bash /vagrant/scripts/prod-like/es-node-install 10.16.0.11 10.16.0.9'
    es.vm.provision :shell, inline: '/bin/bash /vagrant/scripts/vagrant/es-node-install es1'
    es.vm.network 'private_network', ip: '10.16.0.11'
  end

  config.vm.define 'es2' do |es|
    es.vm.provision :shell, inline: '/bin/bash /vagrant/scripts/prod-like/es-node-install 10.16.0.9 10.16.0.11'
    es.vm.provision :shell, inline: '/bin/bash /vagrant/scripts/vagrant/es-node-install es2'
    es.vm.network 'private_network', ip: '10.16.0.9'
  end

end
