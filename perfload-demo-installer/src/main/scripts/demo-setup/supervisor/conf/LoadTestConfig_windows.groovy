
// Host Configurations

hostConfigs {
	localhost {
		perfLoadHome = '..' // relative path
		osfamily = 'windows'
		daemonId = 1
		perfmon = true
		startup = [
			['dir': '..\\ref-app', 'executable': '.\\bin\\ref-app', 'args': ['install']],
			['dir': '..\\ref-app', 'executable': '.\\bin\\ref-app', 'args': ['start']]
		]
		shutdown = [
			['dir': '..\\ref-app', 'executable': '.\\bin\\ref-app', 'args': ['stop']],
			['dir': '..\\ref-app', 'executable': '.\\bin\\ref-app', 'args': ['remove']]
		]
		archiving {
			gcLogs {
				dir = '../ref-app'
				files = 'gclog.txt'
				archiveName = 'gc-logs.zip'
				cleanup = false
			}
			wrapperLog {
				dir = '../ref-app'
				files = 'wrapper.log'
				archiveName = 'wrapper-log.zip'
				cleanup = false
			}
			agentLogs {
				dir = '../agent'
				files = '*.log'
				archiveName = 'agent-logs.zip'
				cleanup = false
			}
		}
	}
}